package org.openstreetmap.josm.plugins.ods.domains.railway;

import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.plugins.ods.entities.Entity;

public interface Switch extends Entity {
    public static boolean recognize(OsmPrimitive primitive) {
        String railway = primitive.get("railway");
        return ("switch".equals(railway)
             || ("construction".equals(railway)) && "switch".equals(primitive.get("construction")));
    }
    
    public String getNumber();

    public void setNumber(String number);

    public String getType();

    public void setType(String type);
}
