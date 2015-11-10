package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;

public abstract class ProrailEntity extends AbstractEntity {
    @Override
    public String getSource() {
        return "Prorail";
    }
}
