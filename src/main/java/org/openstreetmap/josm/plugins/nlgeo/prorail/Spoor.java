package org.openstreetmap.josm.plugins.nlgeo.prorail;

import com.vividsolutions.jts.geom.LineString;

public class Spoor implements ProrailObject {
  private String status;
  private Long id;
  private LineString lineString;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LineString getLineString() {
    return lineString;
  }

  public void setLineString(LineString lineString) {
    this.lineString = lineString;
  }
}
