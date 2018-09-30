package org.openstreetmap.josm.plugins.ods.prorail;

import static org.openstreetmap.josm.plugins.ods.arcgis.rest.config.ServiceType.FeatureServer;

import java.util.Arrays;
import java.util.List;

import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.AGRestConfig;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.DLConfig;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.DSConfig;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.FSConfig;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.config.HostConfig;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtilProj4j;
import org.openstreetmap.josm.plugins.ods.entities.EntityPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OdLayerManager;
import org.openstreetmap.josm.plugins.ods.prorail.ProrailModuleSetup.EntityStores;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.BufferStopPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.PlatformPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RailCrossingPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RailPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.RoadCrossingPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.osm.build.SwitchPrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailKruisingParser;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailOverwegPuntParser;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailPerronParser;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailSpoortakParser;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailStootjukParser;
import org.openstreetmap.josm.plugins.ods.prorail.parsing.ProrailWisselParser;

public class ProrailAGRestSetup extends AGRestConfig {
    private static final String URL = "http://mapservices.prorail.nl/ArcGIS/rest/services";
    final CRSUtil crsUtil;
    // private final GeoUtil geoUtil;
    //    private final EntityStores stores;
    //    private final OdLayerManager odLayerManager;
    //    private OpenDataLayerDownloader downloader;
    //    private final OdBoundaryManager boundaryManager;

    private final List<DLConfig> downloaders;
    private final List<EntityPrimitiveBuilder<?>> primitiveBuilders;

    public ProrailAGRestSetup(OdLayerManager odLayerManager, EntityStores stores) {
        super();
        this.crsUtil = new CRSUtilProj4j();
        //        this.geoUtil = new GeoUtil();

        // Host
        HostConfig geleidingssysteemHost = new HostConfig(URL, "Geleidingssysteem_004", FeatureServer);
        HostConfig basisTopografieHost = new HostConfig(URL, "Basis_topografie_002", FeatureServer);

        // Feature sources
        FSConfig spoortakFS = new FSConfig(geleidingssysteemHost, 2L);
        FSConfig wisselFS = new FSConfig(geleidingssysteemHost, 3L);
        FSConfig kruisingFS = new FSConfig(geleidingssysteemHost, 4L);
        FSConfig stootjukFS = new FSConfig(geleidingssysteemHost, 0L);
        FSConfig perronFS = new FSConfig(basisTopografieHost, 42L);
        FSConfig overwegPuntFS = new FSConfig(basisTopografieHost, 20L);
        //            OdsFeatureSource schermSource = new AGRestFeatureSource(host, "Scherm/10");
        //            OdsFeatureSource deurSource = new AGRestFeatureSource(host, "Deur/9");

        // Data sources
        DSConfig spoortakDS  = new DSConfig(spoortakFS,
                Arrays.asList("ID", "SPOORFUNCTIE"));
        DSConfig wisselDS  = new DSConfig(wisselFS,
                Arrays.asList("ID", "NR", "TYPE"));
        DSConfig kruisingDS  = new DSConfig(kruisingFS,
                Arrays.asList("ID", "KRUISINGNUMMER"));
        DSConfig stootjukDS  = new DSConfig(stootjukFS,
                Arrays.asList("ID", "NR"));
        DSConfig overwegPuntDS  = new DSConfig(overwegPuntFS,
                Arrays.asList("ID", "NAAM"));
        DSConfig perronDS  = new DSConfig(perronFS,
                Arrays.asList("ID", "STATUS"));

        // Parsers
        ProrailSpoortakParser spoortakParser = new ProrailSpoortakParser(crsUtil, stores.rail);
        ProrailWisselParser wisselParser = new ProrailWisselParser(crsUtil, stores.switch_);
        ProrailKruisingParser kruisingParser = new ProrailKruisingParser(crsUtil, stores.railCrossing);
        ProrailStootjukParser stootjukParser = new ProrailStootjukParser(crsUtil, stores.bufferStop);
        ProrailOverwegPuntParser overwegpuntParser = new ProrailOverwegPuntParser(crsUtil, stores.roadCrossing);
        ProrailPerronParser perronParser = new ProrailPerronParser(crsUtil, stores.platform);

        // Downloaders
        DLConfig spoortakDL = new DLConfig(spoortakDS, spoortakParser);
        DLConfig wisselDL = new DLConfig(wisselDS, wisselParser);
        DLConfig kruisingDL = new DLConfig(kruisingDS, kruisingParser);
        DLConfig stootjukDL = new DLConfig(stootjukDS, stootjukParser);
        DLConfig overwegpuntDL = new DLConfig(overwegPuntDS, overwegpuntParser);
        DLConfig perronDL = new DLConfig(perronDS, perronParser);
        this.downloaders = Arrays.asList(spoortakDL, wisselDL, kruisingDL,
                stootjukDL, overwegpuntDL, perronDL);

        // PrimitiveBuilders
        EntityPrimitiveBuilder<?> railBuilder = new RailPrimitiveBuilder(odLayerManager, stores.rail);
        EntityPrimitiveBuilder<?> switchBuilder = new SwitchPrimitiveBuilder(odLayerManager, stores.switch_);
        EntityPrimitiveBuilder<?> railCrossingBuilder = new RailCrossingPrimitiveBuilder(odLayerManager, stores.railCrossing);
        EntityPrimitiveBuilder<?> roadCrossingBuilder = new RoadCrossingPrimitiveBuilder(odLayerManager, stores.roadCrossing);
        EntityPrimitiveBuilder<?> bufferStopBuilder = new BufferStopPrimitiveBuilder(odLayerManager, stores.bufferStop);
        EntityPrimitiveBuilder<?> platformBuilder = new PlatformPrimitiveBuilder(odLayerManager, stores.platform);
        primitiveBuilders = Arrays.asList(railBuilder, switchBuilder, railCrossingBuilder,
                roadCrossingBuilder, bufferStopBuilder, platformBuilder);
    }

    @Override
    public List<DLConfig> getDownloaders() {
        return downloaders;
    }

    @Override
    public List<EntityPrimitiveBuilder<?>> getPrimitiveBuilders() {
        return primitiveBuilders;
    }
}
