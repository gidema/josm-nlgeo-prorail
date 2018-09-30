package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.domains.railway.BufferStop;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.BufferStopImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

import com.vividsolutions.jts.geom.Geometry;

public class ProrailStootjukParser extends ProrailFeatureParser {
    public final OdEntityStore<BufferStop, Long> store;

    public ProrailStootjukParser(OdEntityStore<BufferStop, Long> store) {
        super();
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        BufferStopImpl bufferStop = new BufferStopImpl();
        super.parse(feature, bufferStop, response);
        bufferStop.setId(FeatureUtil.getLong(feature, "ID"));
        bufferStop.setNumber(FeatureUtil.getString(feature, "NR"));
        bufferStop.setSource("Prorail");
        bufferStop.setGeometry((Geometry) feature.getDefaultGeometry());
        store.add(bufferStop);
    }

}
