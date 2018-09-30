package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.domains.railway.Platform;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.PlatformImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

import com.vividsolutions.jts.geom.Geometry;

public class ProrailPerronParser extends ProrailFeatureParser {
    public final OdEntityStore<Platform, Long> store;

    public ProrailPerronParser(CRSUtil crsUtil, OdEntityStore<Platform, Long> store) {
        super(crsUtil);
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        PlatformImpl platform = new PlatformImpl();
        super.parse(feature, platform, response);
        platform.setId(FeatureUtil.getLong(feature, "ID"));
        platform.setSource("Prorail");
        platform.setGeometry((Geometry) feature.getDefaultGeometry());
        store.add(platform);
    }

}
