package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModuleConfiguration;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.entities.EntityRepository;
import org.openstreetmap.josm.plugins.ods.entities.actual.Barrier;
import org.openstreetmap.josm.plugins.ods.entities.actual.Entrance;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerDownloader;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerManager;
import org.openstreetmap.josm.plugins.ods.exceptions.OdsException;
import org.openstreetmap.josm.plugins.ods.gui.OdsDownloadAction;
import org.openstreetmap.josm.plugins.ods.io.Host;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.jts.GeoUtil;
import org.openstreetmap.josm.tools.I18n;

public class ProrailImportModule extends OdsModule {
    private final OdsModuleConfiguration configuration;
    private final static Bounds BOUNDS = new Bounds(50.734, 3.206, 53.583, 7.245);
    private final MainDownloader mainDownloader;
    private GeoUtil geoUtil = new GeoUtil();
    private CRSUtil crsUtil = new CRSUtilProj4j();

    public ProrailImportModule() {
        super();
        this.configuration = new ProrailModuleConfiguration();
        this.mainDownloader = createMainDownloader();
        addAction(new OdsDownloadAction(this));
    }

    private MainDownloader createMainDownloader() {
        MainDownloader downloader = new MainDownloader(this);
        downloader.setOpenDataLayerDownloader(new ProrailOpenDataLayerDownloader(this));
        downloader.setOsmLayerDownloader(new OsmLayerDownloader(this));
        return downloader;
    }

    @Override
    public void initialize() throws OdsException {
        super.initialize();
        for (Host host : getConfiguration().getHosts()) {
            host.initialize();
        }
        mainDownloader.initialize();
    }

    @Override
    public OdsModuleConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String getName() {
        return "Prorail";
    }

    @Override
    public String getDescription() {
        return I18n.tr("ODS module to import rail infrastructure in the Netherlands");
    }

    @Override
    public Bounds getBounds() {
        return BOUNDS;
    }

    @Override
    public GeoUtil getGeoUtil() {
        return geoUtil;
    }

    @Override
    public CRSUtil getCrsUtil() {
        return crsUtil;
    }

    @Override
    public MainDownloader getDownloader() {
        return mainDownloader;
    }

    @Override
    public boolean usePolygonFile() {
        return false;
    }

    @Override
    protected OpenDataLayerManager createOpenDataLayerManager() {
        OpenDataLayerManager manager = new OpenDataLayerManager("Prorail ODS");
//        manager.addEntityStore(new ProrailEntityStore<>(Rail.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(Switch.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(RailCrossing.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(BufferStop.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(Platform.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(RoadCrossing.class, Entity::getPrimaryId));
//        manager.addEntityStore(new ProrailEntityStore<>(Barrier.class, Entity::getPrimaryId));
        EntityRepository repository = manager.getRepository();
        repository.register(Rail.class, "primaryId");
        repository.register(Switch.class, "primaryId");
        repository.register(RailCrossing.class, "primaryId");
        repository.register(BufferStop.class, "primaryId");
        repository.register(Platform.class, "primaryId");
        repository.register(RoadCrossing.class, "primaryId");
        repository.register(Barrier.class, "primaryId");
        repository.register(Entrance.class, "primaryId");
        return manager;
    }

    @Override
    protected OsmLayerManager createOsmLayerManager() {
        return new OsmLayerManager(this, "Prorail OSM");
    }

    @Override
    public Double getTolerance() {
        return 1e-5;
    }
}
