package org.openstreetmap.josm.plugins.nlgeo.prorail;

import com.vividsolutions.jts.geom.Polygon;

public class Kunstwerk implements ProrailObject {
  private Long id;
  private String name;
  private Polygon geoVlak;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Polygon getGeoVlak() {
    return geoVlak;
  }

  public void setGeoVlak(Polygon geoVlak) {
    this.geoVlak = geoVlak;
  }
}
