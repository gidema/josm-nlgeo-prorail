package org.openstreetmap.josm.plugins.nlgeo.prorail;

import java.io.Serializable;

import org.openstreetmap.josm.plugins.openservices.arcgis.rest.ArggisRestDataSet;


public class ProrailDataSet extends ArggisRestDataSet {

  public ProrailDataSet() {
    super(new ProrailJsonParser());
    setMapper(new ProrailToJosmMapper(this));
  }


  @Override
  protected Serializable getId(Object o) {
    if (o instanceof ProrailObject) {
      return ((ProrailObject)o).getId();
    }
    throw new IllegalArgumentException("Unexpected object type");
  }
}
