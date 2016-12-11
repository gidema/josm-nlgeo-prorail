package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;
import org.openstreetmap.josm.plugins.ods.prorail.RoadCrossing;

public class ProrailRoadCrossing extends AbstractEntity implements RoadCrossing {

    @Override
    public Class<RoadCrossing> getBaseType() {
        return RoadCrossing.class;
    }
}
