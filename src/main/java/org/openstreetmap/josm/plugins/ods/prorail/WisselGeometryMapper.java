package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.tags.DefaultGeometryMapper;

public class WisselGeometryMapper extends DefaultGeometryMapper {

    //    @Override
    //    public List<ManagedPrimitive> createPrimitives(Geometry geometry,
    //            Map<String, String> tags, DataSet dataSet) {
    //        if (geometry instanceof MultiLineString) {
    //            List<ManagedPrimitive> primitives = new LinkedList<>();
    //            MultiLineString mls = (MultiLineString) geometry;
    //            for (int i = 0; i < mls.getNumGeometries(); i++) {
    //                primitives.addAll(
    //                        createPrimitives(mls.getGeometryN(i), tags, dataSet));
    //            }
    //            return primitives;
    //        }
    //        return createPrimitives(geometry, tags, dataSet);
    //    }
    //
    //    private List<ManagedPrimitive> createPrimitives(LineString line,
    //            Map<String, String> tags, DataSet dataSet) {
    //        GeometryFactory geoFactory = line.getFactory();
    //        LineSegment previousSegment = null;
    //        List<LineString> lines = new LinkedList<>();
    //        List<Coordinate> coords = new LinkedList<>();
    //        for (int i = 0; i < line.getNumPoints() - 1; i++) {
    //            line.getCoordinateN(i);
    //            LineSegment segment = new LineSegment(line.getCoordinateN(i),
    //                    line.getCoordinateN(i + 1));
    //            if (i == 0) {
    //                coords.add(segment.p0);
    //                coords.add(segment.p1);
    //            } else {
    //                @SuppressWarnings("null")
    //                Double angle = Math.toDegrees(
    //                        Math.abs(segment.angle() - previousSegment.angle()));
    //                if (angle > 45) {
    //                    LineString newLine = geoFactory.createLineString(
    //                            coords.toArray(new Coordinate[0]));
    //                    lines.add(newLine);
    //                    coords = new LinkedList<>();
    //                    coords.add(segment.p0);
    //                    coords.add(segment.p1);
    //                } else {
    //                    coords.add(segment.p1);
    //                }
    //            }
    //            previousSegment = segment;
    //        }
    //        LineString newLine = geoFactory
    //                .createLineString(coords.toArray(new Coordinate[0]));
    //        lines.add(newLine);
    //        List<ManagedPrimitive> primitives = new ArrayList<>(lines.size());
    //        for (LineString ls : lines) {
    //            primitives.add(createPrimitive(ls, tags, dataSet));
    //        }
    //        Geometry newGeometry = geoFactory
    //                .createMultiLineString(lines.toArray(new LineString[0]));
    //        return super.createPrimitives(newGeometry, tags, dataSet);
    //    }
}
