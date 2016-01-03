package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDataSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDownloader;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.LayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailGtRailBuilder;

public class ProrailMainDownloader extends MainDownloader {
    private final ProrailImportModule module;
    private OpenDataLayerDownloader opendataLayerDownloader;
    private LayerDownloader osmLayerDownloader;

    public ProrailMainDownloader(ProrailImportModule module) {
        super();
        this.module = module;
    }
    
    @Override
    public void initialize() throws Exception {
        opendataLayerDownloader = new ProrailOpenDataLayerDownloader(module);
        osmLayerDownloader = new OsmLayerDownloader(module);
    }

    @Override
    protected LayerDownloader getOsmLayerDownloader() {
        return osmLayerDownloader;
    }

    @Override
    public LayerDownloader getOpenDataLayerDownloader() {
        return opendataLayerDownloader;
    }

    private class ProrailOpenDataLayerDownloader extends OpenDataLayerDownloader {
        private final AGRestHost host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
        private final ProrailPrimitiveBuilder primitiveBuilder;
        private final OpenDataLayerManager layerManager;

        @SuppressWarnings("hiding")
        private final OdsModule module;

        public ProrailOpenDataLayerDownloader(OdsModule module) {
            super(module);
            this.module = module;
            this.layerManager = module.getOpenDataLayerManager();
            this.addFeatureDownloader(createRailDownloader());
            this.addFeatureDownloader(createSwitchDownloader());
            this.primitiveBuilder = new ProrailPrimitiveBuilder(module);

        }
        
        @Override
        public void process() {
            try {
                super.process();
                primitiveBuilder.run(getResponse());
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        
        private FeatureDownloader createRailDownloader() {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Spoor/36");
            AGRestDataSource dataSource = new AGRestDataSource(featureSource);
            return new AGRestDownloader<>(dataSource, module.getCrsUtil(), entityBuilder,
                    layerManager.getEntityStore(Rail.class));
        }

        private FeatureDownloader createSwitchDownloader() {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Wisselbeen/37");
            AGRestDataSource dataSource = new AGRestDataSource(featureSource);
            return new AGRestDownloader<>(dataSource, module.getCrsUtil(), entityBuilder,
                    layerManager.getEntityStore(Rail.class));
        }
    }
}
