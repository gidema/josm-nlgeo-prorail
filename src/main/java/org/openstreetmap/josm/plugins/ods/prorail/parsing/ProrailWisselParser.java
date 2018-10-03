package org.openstreetmap.josm.plugins.ods.prorail.parsing;

import org.opengis.feature.simple.SimpleFeature;
import org.openstreetmap.josm.plugins.ods.domains.railway.Switch;
import org.openstreetmap.josm.plugins.ods.domains.railway.impl.SwitchImpl;
import org.openstreetmap.josm.plugins.ods.entities.opendata.FeatureUtil;
import org.openstreetmap.josm.plugins.ods.entities.storage.OdEntityStore;
import org.openstreetmap.josm.plugins.ods.io.DownloadResponse;

import com.vividsolutions.jts.geom.Geometry;

public class ProrailWisselParser extends ProrailFeatureParser {
    public final OdEntityStore<Switch, Long> store;

    public ProrailWisselParser(OdEntityStore<Switch, Long> store) {
        super();
        this.store = store;
    }

    @Override
    public void parse(SimpleFeature feature, DownloadResponse response) {
        SwitchImpl sw = new SwitchImpl();
        super.parse(feature, sw, response);
        sw.setId(FeatureUtil.getLong(feature, "ID"));
        sw.setSource("Prorail");
        sw.setGeometry((Geometry) feature.getDefaultGeometry());
        sw.setType(FeatureUtil.getString(feature, "TYPE"));
        sw.setNumber(FeatureUtil.getString(feature, "NR"));
        store.add(sw);
    }

}
