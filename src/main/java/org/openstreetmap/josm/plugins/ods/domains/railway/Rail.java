package org.openstreetmap.josm.plugins.ods.domains.railway;

import org.openstreetmap.josm.data.osm.OsmPrimitive;

public interface Rail extends ProrailEntity {
    public static boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("rail".equals(railway)
                || ("construction".equals(railway)) && "rail".equals(primitive.get("construction")));
    }
}
