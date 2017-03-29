package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.domains.buildings.Entrance;
import org.openstreetmap.josm.plugins.ods.domains.miscellaneous.Barrier;
import org.openstreetmap.josm.plugins.ods.domains.miscellaneous.EntrancePrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.domains.railway.BufferStop;
import org.openstreetmap.josm.plugins.ods.domains.railway.Platform;
import org.openstreetmap.josm.plugins.ods.domains.railway.Rail;
import org.openstreetmap.josm.plugins.ods.domains.railway.RailCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.RoadCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.Switch;
import org.openstreetmap.josm.plugins.ods.entities.PrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.BarrierPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.BufferStopPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.PlatformPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RailCrossingPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RailPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RoadCrossingPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.SwitchPrimitiveBuilder;

public class ProrailPrimitiveBuilder extends PrimitiveBuilder {

    public ProrailPrimitiveBuilder(OdsModule module) {
        super(module);
        OpenDataLayerManager odLayerManager = module.getOpenDataLayerManager();
        register(Rail.class, new RailPrimitiveBuilder(odLayerManager));
        register(Switch.class, new SwitchPrimitiveBuilder(odLayerManager));
        register(RailCrossing.class, new RailCrossingPrimitiveBuilder(odLayerManager));
        register(BufferStop.class, new BufferStopPrimitiveBuilder(odLayerManager));
        register(Platform.class, new PlatformPrimitiveBuilder(odLayerManager));
        register(RoadCrossing.class, new RoadCrossingPrimitiveBuilder(odLayerManager));
        register(Barrier.class, new BarrierPrimitiveBuilder(odLayerManager));
        register(Entrance.class, new EntrancePrimitiveBuilder(odLayerManager));
    }
}
