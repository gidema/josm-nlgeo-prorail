package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDataSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDownloader;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.LayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailGtRailBuilder;

public class ProrailDownloader extends MainDownloader {
    final ProrailImportModule module;
    final private OpenDataLayerDownloader opendataLayerDownloader;
    final private LayerDownloader osmDownloader;

    public ProrailDownloader(ProrailImportModule module) {
        super();
        this.module = module;
        opendataLayerDownloader = new ProrailOpenDataLayerDownloader(module);
        osmDownloader = new OsmLayerDownloader(module);
    }

    @Override
    protected LayerDownloader getOsmLayerDownloader() {
        return osmDownloader;
    }

    @Override
    public LayerDownloader getOpenDataLayerDownloader() {
        return opendataLayerDownloader;
    }

    private class ProrailOpenDataLayerDownloader extends OpenDataLayerDownloader {
        private final AGRestHost host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);

        public ProrailOpenDataLayerDownloader(OdsModule module) {
            super(module);
            this.addFeatureDownloader(createRailDownloader());
            this.addFeatureDownloader(createSwitchDownloader());
        }
        
        private FeatureDownloader createRailDownloader() {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Spoor/36");
            AGRestDataSource dataSource = new AGRestDataSource(featureSource);
            return new AGRestDownloader(dataSource, module.getCrsUtil(), entityBuilder);
        }

        private FeatureDownloader createSwitchDownloader() {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Wisselbeen/37");
            AGRestDataSource dataSource = new AGRestDataSource(featureSource);
            return new AGRestDownloader(dataSource, module.getCrsUtil(), entityBuilder);
        }
    }
}
