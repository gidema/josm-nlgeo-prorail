package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModulePlugin;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OpenDataLayerManager;
import org.openstreetmap.josm.plugins.ods.entities.osm.OsmLayerManager;
import org.openstreetmap.josm.plugins.ods.gui.OdsDownloadAction;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.plugins.ods.jts.GeoUtil;
import org.openstreetmap.josm.tools.I18n;

public class ProrailImportModule extends OdsModule {
    private final static Bounds BOUNDS = new Bounds(50.734, 3.206, 53.583, 7.245);
    private final MainDownloader mainDownloader;
    private GeoUtil geoUtil = new GeoUtil();
    private CRSUtil crsUtil = new CRSUtilProj4j();
    private ODRailStore railStore = new ODRailStore();

    public ProrailImportModule(OdsModulePlugin plugin) {
        super(plugin);
        this.mainDownloader = new ProrailDownloader(this);
        addAction(new OdsDownloadAction(this));
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

    
    public ODRailStore getRailStore() {
        return railStore;
    }

    @Override
    public boolean usePolygonFile() {
        return false;
    }

    @Override
    public boolean activate() {
        return super.activate();
    }

    @Override
    protected OpenDataLayerManager createOpenDataLayerManager() {
        return new OpenDataLayerManager("Prorail ODS");
    }

    @Override
    protected OsmLayerManager createOsmLayerManager() {
        return new OsmLayerManager(this, "Prorail OSM");
    }
    
    
}
