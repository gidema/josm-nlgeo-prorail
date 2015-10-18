package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDataSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDownloader;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.entities.external.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.external.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.internal.OsmDownloaderNg;
import org.openstreetmap.josm.plugins.ods.entities.internal.OsmEntityBuilder;
import org.openstreetmap.josm.plugins.ods.io.LayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailGtRailBuilder;

public class ProrailDownloader extends MainDownloader {
    private final AGRestHost host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
    final private ProrailImportModule module;

    public ProrailDownloader(ProrailImportModule module) {
        super();
        this.module = module;
    }

    private LayerDownloader osmDownloader;
    @Override
    protected LayerDownloader getOsmLayerDownloader() {
        if (osmDownloader == null) {
            List<OsmEntityBuilder<?>> builders = new LinkedList<>();
            osmDownloader = new OsmDownloaderNg(module.getInternalDataLayer(), builders);
        }
        return osmDownloader;
    }

    private LayerDownloader geotoolsDownloader;
    @Override
    public LayerDownloader getOpenDataLayerDownloader() {
        if (geotoolsDownloader == null) {
            //double tolerance = 0.05;
            List<FeatureDownloader> downloaders = new LinkedList<>();
            downloaders.add(createRailDownloader());
            downloaders.add(createSwitchDownloader());
            return new OpenDataLayerDownloader(module.getExternalDataLayer(), downloaders, null);
        }
        return geotoolsDownloader;
    }

    private FeatureDownloader createRailDownloader() {
        ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil(), module.getRailStore());
        OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Spoor/36");
        AGRestDataSource dataSource = new AGRestDataSource(featureSource);
        return new AGRestDownloader(dataSource, module.getCrsUtil(), entityBuilder, null);
    }

    private FeatureDownloader createSwitchDownloader() {
        ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil(), module.getRailStore());
        OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Wisselbeen/37");
        AGRestDataSource dataSource = new AGRestDataSource(featureSource);
        return new AGRestDownloader(dataSource, module.getCrsUtil(), entityBuilder, null);
    }

}
