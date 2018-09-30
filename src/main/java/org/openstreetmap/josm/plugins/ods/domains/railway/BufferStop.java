package org.openstreetmap.josm.plugins.ods.domains.railway;

import org.openstreetmap.josm.data.osm.OsmPrimitive;

public interface BufferStop extends ProrailEntity {
    public String getNumber();

    public static boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("buffer_stop".equals(railway)
                || ("construction".equals(railway)) && "buffer_stop".equals(primitive.get("construction")));
    }
}
