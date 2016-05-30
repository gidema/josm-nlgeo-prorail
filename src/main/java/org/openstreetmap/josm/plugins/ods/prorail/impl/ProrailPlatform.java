package org.openstreetmap.josm.plugins.ods.prorail.impl;

import org.openstreetmap.josm.plugins.ods.prorail.Platform;
import org.openstreetmap.josm.plugins.ods.prorail.ProrailEntity;

public class ProrailPlatform extends ProrailEntity implements Platform {

    @Override
    public Class<Platform> getBaseType() {
        return Platform.class;
    }
}
