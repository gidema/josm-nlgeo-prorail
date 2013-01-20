package org.openstreetmap.josm.plugins.nlgeo.prorail;

public enum ProrailDataType {
  SPOOR("spoor"), WISSEL("wissel"), WISSEL_NUMMER("wissel nummer"), STOOTJUK("Stootjuk"),
    KUNSTWERK("kunstwerk"), PERRON("perron");
  
  private String name;

  private ProrailDataType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
