package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.plugins.ods.entities.Entity;

public interface Platform extends Entity {
    public static boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("buffer_stop".equals(railway)
             || ("construction".equals(railway)) && "buffer_stop".equals(primitive.get("construction")));
    }
}
