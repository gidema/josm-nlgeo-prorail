package org.openstreetmap.josm.plugins.nlgeo.prorail;

import com.vividsolutions.jts.geom.Point;

public class Stootjuk implements ProrailObject {
  private Long id;
  private Point geoPunt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Point getGeoPunt() {
    return geoPunt;
  }

  public void setGeoPunt(Point geoPunt) {
    this.geoPunt = geoPunt;
  }
}
