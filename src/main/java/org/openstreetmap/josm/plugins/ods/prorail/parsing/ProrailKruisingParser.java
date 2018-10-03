package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.domains.railway.RailCrossing;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.RailCrossingImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

public class ProrailKruisingParser extends ProrailFeatureParser {
    public final OdEntityStore<RailCrossing, Long> store;

    public ProrailKruisingParser(OdEntityStore<RailCrossing, Long> store) {
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        RailCrossingImpl crossing = new RailCrossingImpl();
        super.parse(feature, crossing, response);
        crossing.setId(FeatureUtil.getLong(feature, "ID"));
        crossing.setNumber(FeatureUtil.getString(feature, "KRUISINGNUMMER"));
        store.add(crossing);
    }

}
