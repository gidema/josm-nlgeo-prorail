package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModulePlugin;
import org.openstreetmap.josm.plugins.ods.entities.external.ExternalDataLayer;
import org.openstreetmap.josm.plugins.ods.entities.internal.InternalDataLayer;
import org.openstreetmap.josm.plugins.ods.gui.OdsAction;
import org.openstreetmap.josm.plugins.ods.gui.OdsDownloadAction;
import org.openstreetmap.josm.tools.I18n;

public class ProrailImportModule extends OdsModule {
    private final static Bounds BOUNDS = new Bounds(50.734, 3.206, 53.583, 7.245);
    private List<OdsAction> actions = new LinkedList<>();

    public ProrailImportModule(OdsModulePlugin plugin, org.openstreetmap.josm.plugins.ods.io.OdsDownloader downloader, 
            ExternalDataLayer externalDataLayer, InternalDataLayer internalDataLayer) {
        super(plugin, downloader, externalDataLayer, internalDataLayer);
        actions.add(new OdsDownloadAction(this));
    }

    @Override
    public String getName() {
        return "Prorail";
    }

    @Override
    public String getDescription() {
        return I18n.tr("ODS module to import rail infrastructure in the Netherlands");
    }

    @Override
    public Bounds getBounds() {
        return BOUNDS;
    }

    @Override
    public boolean usePolygonFile() {
        return false;
    }

    @Override
    public List<OdsAction> getActions() {
        return actions;
    }

    @Override
    public void activate() {
        super.activate();
    }
}
