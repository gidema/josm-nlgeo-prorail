package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;
import org.openstreetmap.josm.plugins.ods.prorail.BufferStop;

public class ProrailBufferStop extends AbstractEntity implements BufferStop {

    @Override
    public Class<BufferStop> getBaseType() {
        return BufferStop.class;
    }
}
