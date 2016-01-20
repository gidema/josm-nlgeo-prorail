package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModuleConfiguration;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerManager;
import org.openstreetmap.josm.plugins.ods.gui.OdsDownloadAction;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.jts.GeoUtil;
import org.openstreetmap.josm.tools.I18n;

import exceptions.OdsException;

public class ProrailImportModule extends OdsModule {
    private final OdsModuleConfiguration configuration = new ProrailModuleConfiguration();
    private final static Bounds BOUNDS = new Bounds(50.734, 3.206, 53.583, 7.245);
    private final MainDownloader mainDownloader;
    private GeoUtil geoUtil = new GeoUtil();
    private CRSUtil crsUtil = new CRSUtilProj4j();

    public ProrailImportModule() {
        super();
        this.mainDownloader = new ProrailMainDownloader(this);
        addAction(new OdsDownloadAction(this));
    }

    @Override
    public void initialize() throws OdsException {
        super.initialize();
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
        manager.addEntityStore(Rail.class, new ODRailStore());
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
