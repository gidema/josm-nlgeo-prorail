package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.entities.EntityType;

public class Rail extends ProrailEntity {

    @Override
    public EntityType<Rail> getEntityType() {
        return RailEntityType.getInstance();
    }
}
