package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.domains.railway.Rail;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.RailImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

import com.vividsolutions.jts.geom.Geometry;

public class ProrailSpoortakParser extends ProrailFeatureParser {
    public final OdEntityStore<Rail, Long> store;

    public ProrailSpoortakParser(CRSUtil crsUtil, OdEntityStore<Rail, Long> store) {
        super(crsUtil);
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        RailImpl rail = new RailImpl();
        super.parse(feature, rail, response);
        rail.setId(FeatureUtil.getLong(feature, "ID"));
        rail.setSource("Prorail");
        rail.setGeometry((Geometry) feature.getDefaultGeometry());
        store.add(rail);
    }

}
