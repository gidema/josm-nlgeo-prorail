package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.prorail.ProrailEntity;
import org.openstreetmap.josm.plugins.ods.prorail.RoadCrossing;

public class ProrailRoadCrossing extends ProrailEntity implements RoadCrossing {

    @Override
    public Class<RoadCrossing> getBaseType() {
        return RoadCrossing.class;
    }
}
