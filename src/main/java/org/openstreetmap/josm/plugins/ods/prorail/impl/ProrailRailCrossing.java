package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.prorail.ProrailEntity;
import org.openstreetmap.josm.plugins.ods.prorail.RailCrossing;

public class ProrailRailCrossing extends ProrailEntity implements RailCrossing {
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
