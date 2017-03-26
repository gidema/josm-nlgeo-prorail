package org.openstreetmap.josm.plugins.ods.domains.railway.impl;

import org.openstreetmap.josm.plugins.ods.domains.railway.Rail;
import org.openstreetmap.josm.plugins.ods.entities.AbstractEntity;

public class RailImpl extends AbstractEntity implements Rail {

    @Override
    public Class<Rail> getBaseType() {
        return Rail.class;
    }
}
