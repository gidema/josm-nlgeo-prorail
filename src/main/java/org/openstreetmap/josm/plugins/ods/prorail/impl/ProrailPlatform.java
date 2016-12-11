package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;
import org.openstreetmap.josm.plugins.ods.prorail.Platform;

public class ProrailPlatform extends AbstractEntity implements Platform {

    @Override
    public Class<Platform> getBaseType() {
        return Platform.class;
    }
}
