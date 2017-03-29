package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.Collections;
import java.util.List;

import org.openstreetmap.josm.plugins.ods.OdsDataSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.domains.buildings.Entrance;
import org.openstreetmap.josm.plugins.ods.domains.miscellaneous.Barrier;
import org.openstreetmap.josm.plugins.ods.domains.railway.BufferStop;
import org.openstreetmap.josm.plugins.ods.domains.railway.Platform;
import org.openstreetmap.josm.plugins.ods.domains.railway.Rail;
import org.openstreetmap.josm.plugins.ods.domains.railway.RailCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.RoadCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.Switch;
import org.openstreetmap.josm.plugins.ods.entities.Entity;
import org.openstreetmap.josm.plugins.ods.entities.PrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureDownloader;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.exceptions.OdsException;
import org.openstreetmap.josm.plugins.ods.io.OdsProcessor;

public class ProrailOpenDataLayerDownloader extends OpenDataLayerDownloader {

        private ProrailPrimitiveBuilder primitiveBuilder;

        public ProrailOpenDataLayerDownloader(OdsModule module) {
            super(module);
            this.primitiveBuilder = new ProrailPrimitiveBuilder(getModule());
        }

        @Override
        public void initialize() throws OdsException {
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Spooras/3", Rail.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Wissel/4", Switch.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Kruising/5", RailCrossing.class));
            this.addFeatureDownloader(createDownloader("Geleidingssysteem_001:Stootjuk/1", BufferStop.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Perron/17", Platform.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Overweg/20", RoadCrossing.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Scherm/10", Barrier.class));
            this.addFeatureDownloader(createDownloader("BBK_spoorobjecten_003:Deur/9", Entrance.class));
        }

        private <T extends Entity> FeatureDownloader createDownloader(String sourceName, Class<T> clazz) throws OdsException {
            OdsDataSource dataSource = getModule().getConfiguration().getDataSource(sourceName);
            dataSource.initialize();
            return dataSource.getOdsFeatureSource().getHost().createDownloader(getModule(), dataSource, clazz);
        }

        @Override
        protected PrimitiveBuilder getPrimitiveBuilder() {
            return primitiveBuilder;
        }

        @Override
        protected List<Class<? extends OdsProcessor>> getProcessors() {
            return Collections.emptyList();
        }
    }