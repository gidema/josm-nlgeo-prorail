package org.openstreetmap.josm.plugins.nlgeo.prorail;

import org.json.simple.JSONObject;
import org.openstreetmap.josm.plugins.openservices.arcgis.rest.ArcgisJsonParser;
import org.openstreetmap.josm.plugins.openservices.arcgis.rest.ArcgisRestLayer;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class ProrailJsonParser extends ArcgisJsonParser<ProrailObject> {

  public ProrailJsonParser() {
    super(28992);
  }

  @Override
  public ProrailObject parse(ArcgisRestLayer layer, JSONObject jsonObject) {
    
    Geometry geometry = parseGeometry(layer.getGeometryType(), (JSONObject) jsonObject.get("geometry"));
    JSONObject attributes = (JSONObject) jsonObject.get("attributes");

    String layerName = layer.getName();
    if ("Spoor".equals(layerName)) {
      return parseSpoor(geometry, attributes);
    }
    if ("Wisselbeen".equals(layerName)) {
      return parseWissel(geometry, attributes);
    }
    if ("Stootjuk".equals(layerName)) {
      return parseStootjuk(geometry, attributes);
    }
    if ("Kunstwerk".equals(layerName)) {
      return parseKunstwerk(geometry, attributes);
    }
    
    return null;
  }

  private Spoor parseSpoor(Geometry geometry, JSONObject attributes) {
    MultiLineString mls = (MultiLineString)geometry;
    if (mls.getNumGeometries() > 1) {
      System.out.println("Multipath found in Prorail data");
    }
    LineString lineString = (LineString) mls.getGeometryN(0);
    Spoor spoor = new Spoor();
    spoor.setLineString(lineString);
    spoor.setId((Long) attributes.get("SHAPE.FID"));
    spoor.setStatus((String)attributes.get("STATUS"));
    return spoor;
  }

  private Wissel parseWissel(Geometry geometry, JSONObject attributes) {
    MultiLineString mls = (MultiLineString)geometry;
    if (mls.getNumGeometries() > 1) {
      System.out.println("Multipath found in Prorail data");
    }
    LineString lineString = (LineString) mls.getGeometryN(0);
    Wissel wissel = new Wissel();
    wissel.setLineString(lineString);
    wissel.setId((Long) attributes.get("ID"));
    wissel.setStatus((String)attributes.get("STATUS"));
    return wissel;
  }

  private Stootjuk parseStootjuk(Geometry geometry, JSONObject attributes) {
    Point point = (Point)geometry;
    Stootjuk stootjuk = new Stootjuk();
    stootjuk.setGeoPunt(point);
    stootjuk.setId((Long) attributes.get("ID"));
    return stootjuk;
  }

  private Kunstwerk parseKunstwerk(Geometry geometry, JSONObject attributes) {
    Polygon polygon = (Polygon)geometry;
    Kunstwerk kunstwerk = new Kunstwerk();
    kunstwerk.setGeoVlak(polygon);
    kunstwerk.setId((Long) attributes.get("SHAPE.FID"));
    kunstwerk.setName((String) attributes.get("OBJECTNAAM"));
    return kunstwerk;
  }
}
