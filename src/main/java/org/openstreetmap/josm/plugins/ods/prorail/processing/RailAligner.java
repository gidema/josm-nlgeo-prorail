package org.openstreetmap.josm.plugins.ods.prorail.processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openstreetmap.josm.actions.MergeNodesAction;
import org.openstreetmap.josm.command.Command;
import org.openstreetmap.josm.command.SequenceCommand;
import org.openstreetmap.josm.data.osm.BBox;
import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.data.osm.Node;
import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.data.osm.OsmPrimitiveType;
import org.openstreetmap.josm.data.osm.Way;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OdLayerManager;
import org.openstreetmap.josm.tools.I18n;

public class RailAligner implements Runnable {
    private final static double TOLERANCE = 1e-7;
    private final static Collection<String> railParts = Arrays.asList(
            "rail", "railway_crossing", "switch", "buffer_stop");
    private final OdLayerManager layerManager;
    private final Set<Node> mergedNodes = new HashSet<>();

    public RailAligner(OdLayerManager layerManager) {
        super();
        this.layerManager = layerManager;
    }

    @Override
    public void run() {
        OsmDataLayer dataLayer = layerManager.getOsmDataLayer();
        DataSet dataSet = dataLayer.getDataSet();
        alignRailNodes(dataSet);
        alignRailSegmentStartNodes(dataSet);
        alignRailSegmentEndNodes(dataSet);
    }

    public void alignRailNodes(DataSet dataSet) {
        Collection<Node> railNodes = dataSet.getPrimitives(RailAligner::isRailNode);
        List<Command> commands = new ArrayList<>(railNodes.size());
        for (Node railNode :railNodes) {
            align(railNode, dataSet, commands);
        }
        String cmdName = I18n.tr("Align {0} buffer stop(s)", commands.size());
        Command command = new SequenceCommand(dataSet, cmdName, commands, false);
        command.executeCommand();
    }

    public void alignRailSegmentStartNodes(DataSet dataSet) {
        Collection<Way> ways = dataSet.getPrimitives(RailAligner::isRailSection);
        List<Command> commands = new ArrayList<>(ways.size());
        for (Way way : ways) {
            Node startNode = way.getNode(0);
            align(startNode, dataSet, commands);
        }
        String cmdName = I18n.tr("Align {0} rail node(s)", commands.size());
        Command command = new SequenceCommand(dataSet, cmdName, commands, false);
        command.executeCommand();
    }

    public void alignRailSegmentEndNodes(DataSet dataSet) {
        Collection<Way> ways = dataSet.getPrimitives(RailAligner::isRailSection);
        List<Command> commands = new ArrayList<>(ways.size());
        for (Way way : ways) {
            Node endNode = way.getNode(way.getNodesCount() - 1);
            align(endNode, dataSet, commands);
        }
        String cmdName = I18n.tr("Align {0} rail node(s)", commands.size());
        Command command = new SequenceCommand(dataSet, cmdName, commands, false);
        command.executeCommand();
    }

    private void align(Node railNode, DataSet dataSet, List<Command> commands) {
        // find nearby nodes that are not deleted
        BBox bbox = new BBox(railNode);
        bbox.addPrimitive(railNode, TOLERANCE);
        if (!mergedNodes.contains(railNode)) {
            List<Node> nodes = dataSet.searchNodes(bbox).stream().filter(n -> !n.isDeleted())
                    .collect(Collectors.toList());
            if (nodes.size() > 1) {
                commands.add(MergeNodesAction.mergeNodes(nodes, railNode, railNode));
                mergedNodes.addAll(nodes);
            }
        }
    }

    private static boolean isRailNode(OsmPrimitive osm)
    {
        String value = osm.get("railway");
        return osm.getType() == OsmPrimitiveType.NODE && railParts.contains(value);
    }

    private static boolean isRailSection(OsmPrimitive osm)
    {
        String value = osm.get("railway");
        return osm.getType() == OsmPrimitiveType.WAY && railParts.contains(value);
    }
}
