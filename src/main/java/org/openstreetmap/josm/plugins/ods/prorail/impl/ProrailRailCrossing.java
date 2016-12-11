package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;
import org.openstreetmap.josm.plugins.ods.prorail.RailCrossing;

public class ProrailRailCrossing extends AbstractEntity implements RailCrossing {
    private String type;
    private String number;

    
    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Class<RailCrossing> getBaseType() {
        return RailCrossing.class;
    }
}
