package org.openstreetmap.josm.plugins.nlgeo.prorail;

import java.io.Serializable;

import org.openstreetmap.josm.plugins.openservices.arcgis.rest.ArggisRestDataSet;


public class ProrailDataSet extends ArggisRestDataSet<ProrailObject> {

  public ProrailDataSet() {
    super(new ProrailJsonParser());
    setMapper(new ProrailToJosmMapper(this));
  }


  @Override
  protected Serializable getId(ProrailObject o) {
    return o.getId();
  }
}
