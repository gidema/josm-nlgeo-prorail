package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.entities.EntityStore;
import org.openstreetmap.josm.plugins.ods.entities.EntityPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;
import org.openstreetmap.josm.plugins.ods.osm.OsmPrimitiveFactory;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.ProrailRailPrimitiveBuilder;

public class ProrailPrimitiveBuilder {
    private OdsModule module;
    private EntityPrimitiveBuilder<Rail> railPrimitiveBuilder;

    public ProrailPrimitiveBuilder(OdsModule module) {
        this.module = module;
        OpenDataLayerManager odLayerManager = module.getOpenDataLayerManager();
        railPrimitiveBuilder = new ProrailRailPrimitiveBuilder(odLayerManager);
    }
    
    public void run(DownloadResponse response) {
        EntityStore<Rail> railStore = module.getOpenDataLayerManager()
                .getEntityStore(Rail.class);
        for (Rail rail : railStore) {
            if (rail.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
                railPrimitiveBuilder.createPrimitive(rail);
            }
        }
    }
}
