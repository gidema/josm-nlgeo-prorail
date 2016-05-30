package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.prorail.ProrailEntity;
import org.openstreetmap.josm.plugins.ods.prorail.Rail;

public class ProrailRail extends ProrailEntity implements Rail {

    @Override
    public Class<Rail> getBaseType() {
        return Rail.class;
    }
}
