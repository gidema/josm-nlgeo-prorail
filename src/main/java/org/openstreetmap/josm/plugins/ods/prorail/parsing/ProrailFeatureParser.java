package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.openstreetmap.josm.plugins.ods.crs.CRSException;
import org.openstreetmap.josm.plugins.ods.crs.CRSUtil;
import org.openstreetmap.josm.plugins.ods.entities.OdEntity;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;
import org.openstreetmap.josm.plugins.ods.parsing.FeatureParser;

import com.vividsolutions.jts.geom.Geometry;

public abstract class ProrailFeatureParser implements FeatureParser {

    public ProrailFeatureParser() {
        super();
    }

    @Override
    public void parse(SimpleFeatureCollection downloadedFeatures, DownloadResponse response) {
        try (
                FeatureIterator<SimpleFeature> it = downloadedFeatures.features();
                ) {
            while (it.hasNext()) {
                parse(it.next(), response);
            }
        }
    }

    public abstract void parse(SimpleFeature feature, DownloadResponse response);

    protected void parse(SimpleFeature feature, OdEntity entity, DownloadResponse response) {
        //        entity.setDownloadResponse(response);
        entity.setPrimaryId(feature.getID());
        //        LocalDate date = response.getRequest().getDownloadTime().toLocalDate();
        //        if (date != null) {
        //            entity.setSourceDate(DateTimeFormatter.ISO_LOCAL_DATE.format(date));
        //        }
        entity.setSource("Prorail");
        try {
            CoordinateReferenceSystem crs = feature.getType()
                    .getCoordinateReferenceSystem();
            Geometry gtGeometry = getGeometry(feature);
            CRSUtil crsUtil = CRSUtil.getInstance();
            entity.setGeometry(crsUtil.toOsm(gtGeometry, crs));
        } catch (CRSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-method")
    protected Geometry getGeometry(SimpleFeature feature) {
        return (Geometry) feature.getDefaultGeometry();
    }
}
