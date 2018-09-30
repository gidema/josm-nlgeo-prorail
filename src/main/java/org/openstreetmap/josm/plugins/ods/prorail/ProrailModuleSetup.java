package org.openstreetmap.josm.plugins.ods.prorail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestInitializer;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.AGRestConfig;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.domains.railway.BufferStop;
import org.openstreetmap.josm.plugins.ods.domains.railway.Platform;
import org.openstreetmap.josm.plugins.ods.domains.railway.Rail;
import org.openstreetmap.josm.plugins.ods.domains.railway.RailCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.RoadCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.Switch;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OdBoundaryManager;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OdLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.entities.storage.SimpleOdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.io.OsmLayerDownloader;
import org.openstreetmap.josm.plugins.ods.setup.ModuleSetup;
import org.openstreetmap.josm.plugins.ods.setup.OsmSetup;
import org.openstreetmap.josm.plugins.ods.setup.SimpleOsmSetup;

public class ProrailModuleSetup implements ModuleSetup {
    private final EntityStores entityStores;
    private final OdLayerManager odLayerManager;
    private final OsmLayerManager osmLayerManager;
    private final OsmSetup osmSetup;
    private MainDownloader mainDownloader;
    private final AGRestConfig agRestConfig;
    private final CRSUtil crsUtil = new CRSUtilProj4j();
    private OpenDataLayerDownloader odLayerDownloader;

    public ProrailModuleSetup() {
        this.entityStores = new EntityStores();
        this.odLayerManager = new OdLayerManager("Prorail ODS");
        this.osmLayerManager = new OsmLayerManager("Prorail OSM");
        this.osmSetup = new SimpleOsmSetup(osmLayerManager);
        this.agRestConfig = new ProrailAGRestSetup(odLayerManager, entityStores);
        setup();
    }

    private void setup() {
        AGRestInitializer initializer = new AGRestInitializer();
        try {
            initializer.initialize(agRestConfig, crsUtil);
            List<Runnable> processors = new ArrayList<>(6);
            processors.addAll(agRestConfig.getPrimitiveBuilders());
            OdBoundaryManager boundaryManager = new OdBoundaryManager(entityStores.odEntityStores());
            this.odLayerDownloader = new OpenDataLayerDownloader(
                    odLayerManager, initializer.getDownloaders(), processors, boundaryManager);
            mainDownloader = new MainDownloader(this, getOsmLayerDownloader(), odLayerDownloader, Collections.emptyList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public OdLayerManager getOdLayerManager() {
        return odLayerManager;
    }

    @Override
    public OsmLayerManager getOsmLayerManager() {
        return osmLayerManager;
    }

    @Override
    public OpenDataLayerDownloader getOdLayerDownloader() {
        return odLayerDownloader;
    }

    @Override
    public OsmLayerDownloader getOsmLayerDownloader() {
        return osmSetup.getOsmLayerDownloader();
    }

    @Override
    public MainDownloader getMainDownloader() {
        return mainDownloader;
    }

    protected class EntityStores {
        final public OdEntityStore<Rail, Long> rail;
        final public OdEntityStore<Switch, Long> switch_;
        final public OdEntityStore<RailCrossing, Long> railCrossing;
        final public OdEntityStore<RoadCrossing, Long> roadCrossing;
        final public OdEntityStore<BufferStop, Long> bufferStop;
        final public OdEntityStore<Platform, Long> platform;

        public EntityStores() {
            this.rail = new SimpleOdEntityStore<>(Rail::getId);
            this.switch_ = new SimpleOdEntityStore<>(Switch::getId);
            this.railCrossing = new SimpleOdEntityStore<>(RailCrossing::getId);
            this.roadCrossing = new SimpleOdEntityStore<>(RoadCrossing::getId);
            this.bufferStop = new SimpleOdEntityStore<>(BufferStop::getId);
            this.platform = new SimpleOdEntityStore<>(Platform::getId);
        }

        public List<OdEntityStore<?, ?>> odEntityStores() {
            return Arrays.asList(rail, switch_, railCrossing, roadCrossing,
                    bufferStop, platform);
        }
    }
}
