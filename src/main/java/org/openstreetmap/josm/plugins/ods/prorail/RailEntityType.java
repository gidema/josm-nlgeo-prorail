package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.plugins.ods.entities.EntityType;

public class RailEntityType implements EntityType<Rail> {
    private final static RailEntityType INSTANCE = new RailEntityType();
    
    private RailEntityType() {
        // Hide default constructor
    }

    public static RailEntityType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("rail".equals(railway)
             || ("construction".equals(railway)) && "rail".equals(primitive.get("construction")));
    }

    @Override
    public Class<Rail> getEntityClass() {
        return Rail.class;
    }

}
