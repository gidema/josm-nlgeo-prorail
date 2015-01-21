package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModulePlugin;
import org.openstreetmap.josm.plugins.ods.PrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDataSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDownloader;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.entities.external.ExternalDataLayer;
import org.openstreetmap.josm.plugins.ods.entities.external.GeotoolsDownloadJob;
import org.openstreetmap.josm.plugins.ods.entities.internal.InternalDataLayer;
import org.openstreetmap.josm.plugins.ods.entities.internal.OsmDownloadJob;
import org.openstreetmap.josm.plugins.ods.entities.internal.OsmDownloader;
import org.openstreetmap.josm.plugins.ods.entities.internal.OsmEntityBuilder;
import org.openstreetmap.josm.plugins.ods.io.Downloader;
import org.openstreetmap.josm.plugins.ods.io.OdsDownloader;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailGtRailBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.ProrailRailPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.tasks.Task;

public class ProrailImportModuleConfig {
    private final OdsModulePlugin plugin;
    private final AGRestHost host;
    private final CRSUtil crsUtil;
    private final InternalDataLayer internalDataLayer;
    private final ExternalDataLayer externalDataLayer;
    
    private final GtRailStore gtRailStore = new GtRailStore();
    
    public ProrailImportModuleConfig(OdsModulePlugin plugin) {
        this.plugin = plugin;

        host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
        crsUtil = new CRSUtilProj4j();

        internalDataLayer = new InternalDataLayer("Prorail OSM");
        externalDataLayer = new ExternalDataLayer("Prorail ODS");
        
    }

    public OdsModule getModule() {
        return new ProrailImportModule(plugin, createOdsDownloader(), externalDataLayer, internalDataLayer);
    }

    private OdsDownloader createOdsDownloader() {
        List<Task> tasks = new ArrayList<>(0);
        return new OdsDownloader(createOsmDownloadJob(), createGeotoolsDownloadJob(), tasks);
    }
    
    private OsmDownloadJob createOsmDownloadJob() {
        List<Task> tasks = new ArrayList<>(0);
        // For the prorail data, we only import the external data
        // We don't build entities from the OSM data, so we use an
        // empty list of Entity builders
        List<OsmEntityBuilder<?>> builders = new LinkedList<>();
        OsmDownloader downloader = new OsmDownloader(builders);
        return new OsmDownloadJob(internalDataLayer, downloader, tasks);
    }

    private GeotoolsDownloadJob createGeotoolsDownloadJob() {
        List<Downloader> downloaders = new LinkedList<>();
        downloaders.add(createRailDownloader(null));
        downloaders.add(createSwitchDownloader(null));
        PrimitiveBuilder<Rail> railPrimitiveBuilder = new ProrailRailPrimitiveBuilder(externalDataLayer);
        List<Task> tasks = new LinkedList<>();
        tasks.add(new CreateRailPrimitivesTask(gtRailStore, railPrimitiveBuilder));
        return new GeotoolsDownloadJob(externalDataLayer, downloaders, tasks);
    }

    private Downloader createRailDownloader(List<Task> tasks) {
        ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(crsUtil, gtRailStore);
        OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Spoor/36");
        AGRestDataSource dataSource = new AGRestDataSource(featureSource);
        return new AGRestDownloader(dataSource, crsUtil, entityBuilder, gtRailStore, 
                (tasks == null ? new ArrayList<Task>(0) : tasks));
    }

    private Downloader createSwitchDownloader(List<Task> tasks) {
        ProrailGtRailBuilder entityBuilder = new ProrailGtRailBuilder(crsUtil, gtRailStore);
        OdsFeatureSource featureSource = new AGRestFeatureSource(host, "Wisselbeen/37");
        AGRestDataSource dataSource = new AGRestDataSource(featureSource);
        return new AGRestDownloader(dataSource, crsUtil, entityBuilder, gtRailStore, 
                (tasks == null ? new ArrayList<Task>(0) : tasks));
    }
}
