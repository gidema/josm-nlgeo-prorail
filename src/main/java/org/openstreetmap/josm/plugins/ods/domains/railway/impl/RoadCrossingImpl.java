package org.openstreetmap.josm.plugins.ods.domains.railway.impl;

import org.openstreetmap.josm.plugins.ods.domains.railway.RoadCrossing;
import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;

public class RoadCrossingImpl extends AbstractEntity implements RoadCrossing {

    @Override
    public Class<RoadCrossing> getBaseType() {
        return RoadCrossing.class;
    }
}
