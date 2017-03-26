package org.openstreetmap.josm.plugins.ods.domains.railway.impl;

import org.openstreetmap.josm.plugins.ods.domains.railway.Platform;
import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;

public class PlatformImpl extends AbstractEntity implements Platform {

    @Override
    public Class<Platform> getBaseType() {
        return Platform.class;
    }
}
