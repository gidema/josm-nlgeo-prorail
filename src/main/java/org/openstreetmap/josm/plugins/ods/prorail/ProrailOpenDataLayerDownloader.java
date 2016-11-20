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
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Spooras/3", Rail.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Wissel/4", Switch.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Kruising/5", RailCrossing.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Stootjuk/1", BufferStop.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Perron/17", Platform.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Overweg/20", RoadCrossing.class));
//            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Scherm/10", Barrier.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Deur/9", Entrance.class));
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