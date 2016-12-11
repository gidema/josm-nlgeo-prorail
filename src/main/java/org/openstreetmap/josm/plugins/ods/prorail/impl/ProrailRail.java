package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;
import org.openstreetmap.josm.plugins.ods.prorail.Rail;

public class ProrailRail extends AbstractEntity implements Rail {

    @Override
    public Class<Rail> getBaseType() {
        return Rail.class;
    }
}
