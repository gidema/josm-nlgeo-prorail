package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.domains.railway.RoadCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.RoadCrossingImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

import com.vividsolutions.jts.geom.Geometry;

public class ProrailOverwegPuntParser extends ProrailFeatureParser {
    public final OdEntityStore<RoadCrossing, Long> store;

    public ProrailOverwegPuntParser(CRSUtil crsUtil, OdEntityStore<RoadCrossing, Long> store) {
        super(crsUtil);
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        RoadCrossingImpl crossing = new RoadCrossingImpl();
        super.parse(feature, crossing, response);
        crossing.setId(FeatureUtil.getLong(feature, "ID"));
        crossing.setSource("Prorail");
        crossing.setGeometry((Geometry) feature.getDefaultGeometry());
        store.add(crossing);
    }
}
