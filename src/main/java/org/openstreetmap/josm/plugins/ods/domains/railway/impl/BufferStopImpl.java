package org.openstreetmap.josm.plugins.ods.domains.railway.impl;

import org.openstreetmap.josm.plugins.ods.domains.railway.BufferStop;
import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;

public class BufferStopImpl extends AbstractEntity implements BufferStop {

    @Override
    public Class<BufferStop> getBaseType() {
        return BufferStop.class;
    }
}
