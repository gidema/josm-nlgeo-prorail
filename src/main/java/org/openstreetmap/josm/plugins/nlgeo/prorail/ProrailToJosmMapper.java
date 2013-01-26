package org.openstreetmap.josm.plugins.nlgeo.prorail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.data.osm.Node;
import org.openstreetmap.josm.data.osm.Relation;
import org.openstreetmap.josm.data.osm.Way;
import org.openstreetmap.josm.plugins.openservices.JosmObjectFactory;
import org.openstreetmap.josm.plugins.openservices.ObjectToJosmMapper;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class ProrailToJosmMapper implements ObjectToJosmMapper {
  private final JosmObjectFactory objectFactory;

  public ProrailToJosmMapper(DataSet dataSet) {
    this.objectFactory = new JosmObjectFactory(dataSet, 28992);
  }
  
  @Override
  public void create(Object prorailObject) {
    if (prorailObject instanceof Spoor) {
      createSpoor((Spoor)prorailObject);
      return;
    }
    if (prorailObject instanceof Wissel) {
      createWissel((Wissel)prorailObject);
      return;
    }
    if (prorailObject instanceof Stootjuk) {
      createStootjuk((Stootjuk)prorailObject);
      return;
    }
    if (prorailObject instanceof Kunstwerk) {
      createKunstwerk((Kunstwerk)prorailObject);
      return;
    }
    throw new IllegalArgumentException("Unexpected object type");
  }
  
  private void createSpoor(Spoor spoor) {
    LineString lineString = spoor.getLineString();
    Way way = objectFactory.buildWay(lineString);
    Map<String, String> keys = way.getKeys();
    keys.put("railway", "rail");
    keys.put("gauge", "1435");
    keys.put("electrified", "contact_line");
    keys.put("voltage", "1500");
    keys.put("frequency", "0");
    keys.put("source", "Prorail");
    way.setKeys(keys);
    return;
  }

  private void createWissel(Wissel wissel) {
    MultiLineString sections = splitSwitchLine(wissel.getLineString());
    for (int i=0; i<sections.getNumGeometries(); i++) {
      LineString section = (LineString) sections.getGeometryN(i);
      Way way = objectFactory.buildWay(section);
      Map<String, String> keys = way.getKeys();
      keys.put("railway", "rail");
      keys.put("gauge", "1435");
      keys.put("electrified", "contact_line");
      keys.put("voltage", "1500");
      keys.put("frequency", "0");
      keys.put("source", "Prorail");
      way.setKeys(keys);
    }
  }
  
  private void createStootjuk(Stootjuk stootjuk) {
    Point point = stootjuk.getGeoPunt();
    Node node = objectFactory.buildNode(point, true);
    Map<String, String> keys = node.getKeys();
    keys.put("railway", "buffer_stop");
    node.setKeys(keys);
  }
  
  private void createKunstwerk(Kunstwerk kunstwerk) {
    Polygon polygon = kunstwerk.getGeoVlak();
    Relation relation = objectFactory.buildMultiPolygon(polygon);
    Map<String, String> keys = relation.getKeys();
    keys.put("name", kunstwerk.getName());
    relation.setKeys(keys);
  }
  
  /**
   * The LineString that defines a switch (Wissel) in the Prorail data isn't suitable for
   * OSM.
   * The line continues over switch joints and may self-intersect. This method splits the line
   * in smaller parts at points where the angle between two segments is less than 135 degrees.
   * 
   * @param wisselLine
   * @return the split line as a multiLine
   */
  private static MultiLineString splitSwitchLine(LineString line) {
    GeometryFactory geoFactory = line.getFactory();
    LineSegment previousSegment = null;
    List<LineString> lines = new LinkedList<LineString>();
    List<Coordinate> coords = new LinkedList<Coordinate>();
    for (int i=0; i<line.getNumPoints() - 1; i++) {
      line.getCoordinateN(i);
      LineSegment segment = new LineSegment(line.getCoordinateN(i), line.getCoordinateN(i+1));
      if (i == 0) {
        coords.add(segment.p0);
        coords.add(segment.p1);
      }
      else {
        @SuppressWarnings("null")
        Double angle = Math.toDegrees(Math.abs(segment.angle() - previousSegment.angle()));
        if (angle > 45) {
          LineString newLine = geoFactory.createLineString(coords.toArray(new Coordinate[0]));
          lines.add(newLine);
          coords = new LinkedList<Coordinate>();
          coords.add(segment.p0);
          coords.add(segment.p1);
        }
        else {
          coords.add(segment.p1);
        }
      }
      previousSegment = segment;
    }
    LineString newLine = geoFactory.createLineString(coords.toArray(new Coordinate[0]));
    lines.add(newLine);
    return geoFactory.createMultiLineString(lines.toArray(new LineString[0]));
  }
}
