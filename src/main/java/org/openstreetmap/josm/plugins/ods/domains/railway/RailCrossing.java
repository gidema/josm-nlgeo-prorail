package org.openstreetmap.josm.plugins.ods.domains.railway;

public interface RailCrossing extends ProrailEntity {
    public String getNumber();
    public void setNumber(String number);
    public String getType();
    public void setType(String type);
}
