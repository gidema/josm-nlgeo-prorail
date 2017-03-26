package org.openstreetmap.josm.plugins.ods.domains.railway;

import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.plugins.ods.entities.Entity;

public interface Rail extends Entity {
    public static boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("rail".equals(railway)
             || ("construction".equals(railway)) && "rail".equals(primitive.get("construction")));
    }
}
