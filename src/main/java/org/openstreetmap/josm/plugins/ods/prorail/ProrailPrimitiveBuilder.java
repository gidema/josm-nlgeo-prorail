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
//    private EntityPrimitiveBuilder<Rail> railPrimitiveBuilder;
//    private EntityPrimitiveBuilder<Switch> switchPrimitiveBuilder;
//    private EntityPrimitiveBuilder<RailCrossing> railCrossingPrimitiveBuilder;
//    private EntityPrimitiveBuilder<BufferStop> bufferStopPrimitiveBuilder;
//    private EntityPrimitiveBuilder<Platform> platformPrimitiveBuilder;
//    private EntityPrimitiveBuilder<RoadCrossing> roadCrossingPrimitiveBuilder;
//    private EntityPrimitiveBuilder<Barrier> barrierPrimitiveBuilder;

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
    
//    public void run(DownloadResponse response) {
//        // TODO generalize this process in a higher level class
//        EntityStore<Rail> railStore = module.getOpenDataLayerManager()
//                .getEntityStore(Rail.class);
//        EntityStore<Switch> switchStore = module.getOpenDataLayerManager()
//                .getEntityStore(Switch.class);
//        EntityStore<RailCrossing> railCrossingStore = module.getOpenDataLayerManager()
//                .getEntityStore(RailCrossing.class);
//        EntityStore<BufferStop> bufferStopStore = module.getOpenDataLayerManager()
//                .getEntityStore(BufferStop.class);
//        EntityStore<Platform> platformStore = module.getOpenDataLayerManager()
//                .getEntityStore(Platform.class);
//        EntityStore<RoadCrossing> roadCrossingStore = module.getOpenDataLayerManager()
//                .getEntityStore(RoadCrossing.class);
//        for (Rail rail : railStore) {
//            if (rail.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
//                railPrimitiveBuilder.createPrimitive(rail);
//            }
//        }
//        for (Switch oSwitch : switchStore) {
//            if (oSwitch.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
//                switchPrimitiveBuilder.createPrimitive(oSwitch);
//            }
//        }
//        for (RailCrossing railCrossing : railCrossingStore) {
//            if (railCrossing.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
//                railCrossingPrimitiveBuilder.createPrimitive(railCrossing);
//            }
//        }
//        for (BufferStop bufferStop : bufferStopStore) {
//            if (bufferStop.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
//                bufferStopPrimitiveBuilder.createPrimitive(bufferStop);
//            }
//        }
//        for (Platform platform : platformStore) {
//            if (platform.getPrimitive() == null) {
//                platformPrimitiveBuilder.createPrimitive(platform);
//            }
//        }
//        for (RoadCrossing roadCrossing: roadCrossingStore) {
//            if (roadCrossing.getPrimitive() == null) {
//                roadCrossingPrimitiveBuilder.createPrimitive(roadCrossing);
//            }
//        }
//    }
//    
//    private <T extends Entity> void dummy(Class<T> entityClass) {
//        EntityStore<T> entityStore = module.getOpenDataLayerManager()
//                .getEntityStore(entityClass);
//        for (Rail rail : railStore) {
//            if (rail.getPrimitive() == null /*&& !rail.isIncomplete()*/) {
//                railPrimitiveBuilder.createPrimitive(rail);
//            }
//        }
//
//    }
}
