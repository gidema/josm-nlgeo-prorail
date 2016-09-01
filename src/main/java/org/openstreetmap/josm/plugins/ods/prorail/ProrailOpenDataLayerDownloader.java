package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.concurrent.ExecutionException;

import org.openstreetmap.josm.plugins.ods.OdsDataSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.entities.Entity;
import org.openstreetmap.josm.plugins.ods.entities.actual.Barrier;
import org.openstreetmap.josm.plugins.ods.entities.actual.Entrance;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.exceptions.OdsException;

public class ProrailOpenDataLayerDownloader extends OpenDataLayerDownloader {

        private ProrailPrimitiveBuilder primitiveBuilder;

        public ProrailOpenDataLayerDownloader(OdsModule module) {
            super(module);
        }

        @Override
        public void initialize() throws OdsException {
//            super.initialize();
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Spooras/34", Rail.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Wissel/35", Switch.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Kruising/36", RailCrossing.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Stootjuk/32", BufferStop.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Perron/20", Platform.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Overweg/29", RoadCrossing.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Scherm/12", Barrier.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_001:Deur/11", Entrance.class));
            this.primitiveBuilder = new ProrailPrimitiveBuilder(getModule());
        }

        @Override
        public void process() throws ExecutionException {
//            try {
                super.process();
                primitiveBuilder.run(getResponse());
                updateLayer();
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw e;
//            }
        }
    
        private <T extends Entity> FeatureDownloader createDownloader(String sourceName, Class<T> clazz) throws OdsException {
            OdsDataSource dataSource = getModule().getConfiguration().getDataSource(sourceName);
            dataSource.initialize();
            return dataSource.getOdsFeatureSource().getHost().createDownloader(getModule(), dataSource, clazz);
        }
    }