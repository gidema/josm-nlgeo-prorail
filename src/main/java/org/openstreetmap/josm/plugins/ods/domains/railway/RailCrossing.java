package org.openstreetmap.josm.plugins.ods.domains.railway;

import org.openstreetmap.josm.plugins.ods.entities.Entity;

public interface RailCrossing extends Entity {
    public String getNumber();
    public void setNumber(String number);
    public String getType();
    public void setType(String type);
}
