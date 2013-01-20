// License: GPL. Copyright 2007 by Immanuel Scholz and others
package org.openstreetmap.josm.plugins.nlgeo.prorail;


import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.openservices.CustomDataLayer;
import org.openstreetmap.josm.plugins.openservices.arcgis.rest.DownloadArcgisRestTask;
import org.openstreetmap.josm.plugins.openservices.arcgis.rest.RestQuery;

/**
 * Task allowing to download GPS data.
 */
public class DownloadProrailTask extends DownloadArcgisRestTask {
  private final static String HOST_URL = "http://mapservices.prorail.nl/ArcGIS/rest/services";

  private final ProrailDataType type;

  /**
   * Task to download Prorail data.
   * 
   * @param type
   */
  public DownloadProrailTask(ProrailDataType type) {
    this.type = type;
  }

  @Override
  protected RestQuery getQuery(Bounds bounds) {
    RestQuery query = null;

    query = new RestQuery();
   query.setHost(HOST_URL);
    switch (type) {
    case SPOOR:
      query.setService("BBK_spoorobjecten");
      query.setLayer(36L);
      query.setOutFields("STATUS,OBJECTNAAM,SHAPE.FID");
      this.setLayerName("spoor");
      break;
    case WISSEL:
      query.setService("BBK_spoorobjecten");
      query.setLayer(37L);
      query.setOutFields("STATUS,OBJECTTYPE,ID");
      this.setLayerName("spoor");
      break;
    case STOOTJUK:
      query.setService("BBK_spoorobjecten");
      query.setLayer(33L);
      query.setOutFields("ID");
      this.setLayerName("spoor");
      break;
    case KUNSTWERK:
      query.setService("BBK_overige_objecten");
      query.setLayer(6L);
      query.setOutFields("OBJECTNAAM,SHAPE.FID");
      this.setLayerName("kunstwerk");
      break;
    }
    return query;
  }

  @Override
  public CustomDataLayer createTargetLayer(String name) {
    return new CustomDataLayer(new ProrailDataSet(), name);
  }
}
