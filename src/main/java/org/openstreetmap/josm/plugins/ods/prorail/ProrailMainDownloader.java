package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.Collection;

import org.openstreetmap.josm.plugins.ods.OdsDataSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModuleConfiguration;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.Host;
import org.openstreetmap.josm.plugins.ods.io.LayerDownloader;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailGtRailBuilder;

import exceptions.OdsException;

public class ProrailMainDownloader extends MainDownloader {
    private final OpenDataLayerDownloader opendataLayerDownloader;
    private final LayerDownloader osmLayerDownloader;

    public ProrailMainDownloader(ProrailImportModule module) {
        super();
        this.opendataLayerDownloader = new ProrailOpenDataLayerDownloader(module);
        this.osmLayerDownloader = new OsmLayerDownloader(module);

    }
    
    @Override
    public void initialize() throws OdsException {
        opendataLayerDownloader.initialize();
        osmLayerDownloader.initialize();
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
//        private final AGRestHost host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
        private ProrailPrimitiveBuilder primitiveBuilder;
        private OpenDataLayerManager layerManager;
//        private final List<AbstractHost> hosts = Arrays.asList(host);

        private final OdsModule module;

        public ProrailOpenDataLayerDownloader(OdsModule module) {
            super(module);
            this.module = module;
        }

        @Override
        public void initialize() throws OdsException {
            this.layerManager = module.getOpenDataLayerManager();
            this.primitiveBuilder = new ProrailPrimitiveBuilder(module);
            OdsModuleConfiguration configuration = module.getConfiguration();
            this.addFeatureDownloader(createRailDownloader(configuration));
            this.addFeatureDownloader(createSwitchDownloader(configuration));
        }

        @Override
        protected Collection<? extends Host> getHosts() {
            return module.getConfiguration().getHosts();
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
        
        private FeatureDownloader createRailDownloader(OdsModuleConfiguration configuration) throws OdsException {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
//            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Spoor/36");
            OdsDataSource dataSource = configuration.getDataSource("spoor");
            return new AGRestDownloader<>(dataSource, module.getCrsUtil(), entityBuilder,
                    layerManager.getEntityStore(Rail.class));
        }

        private FeatureDownloader createSwitchDownloader(OdsModuleConfiguration configuration) throws OdsException {
            ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(module.getCrsUtil());
//            OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Wisselbeen/37");
//            AGRestDataSource dataSource = new AGRestDataSource(featureSource);
            OdsDataSource dataSource = configuration.getDataSource("wisselbeen");
            return new AGRestDownloader<>(dataSource, module.getCrsUtil(), entityBuilder,
                    layerManager.getEntityStore(Rail.class));
        }
    }
}
