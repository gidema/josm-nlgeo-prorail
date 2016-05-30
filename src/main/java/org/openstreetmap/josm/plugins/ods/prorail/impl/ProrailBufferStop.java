package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.prorail.BufferStop;
import org.openstreetmap.josm.plugins.ods.prorail.ProrailEntity;

public class ProrailBufferStop extends ProrailEntity implements BufferStop {

    @Override
    public Class<BufferStop> getBaseType() {
        return BufferStop.class;
    }
}
