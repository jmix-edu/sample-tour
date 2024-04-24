package com.company.tour.app;

import io.jmix.maps.utils.GeometryUtils;
import org.locationtech.jts.geom.*;

import static io.jmix.maps.utils.GeometryUtils.createPoint;

public final class OfficePlanUtils {

    private static final GeometryFactory factory = GeometryUtils.getGeometryFactory();

    public static final String VECTOR_FILL_COLOR = "rgba(54, 49, 168, 0.2)";
    public static final String VECTOR_STROKE_COLOR = "rgba(20, 16, 109, 1)";
    public static final String MULTI_POLYGON_FILL_COLOR = "rgba(235, 41, 55, 0.2)";
    public static final String MULTI_POLYGON_STROKE_COLOR = "rgba(153, 13, 24, 1)";

    public static Point stairPoint = factory.createPoint(new Coordinate(408.20312500000006, 501.8945312500001));
    public static MultiPoint freeSitsPoint = factory.createMultiPoint(new Point[]{
            createPoint(226.5625, 358.33984375000006),
            createPoint(226.5625, 330.01953125000006),
            createPoint(304.6875, 358.33984375000006),
            createPoint(304.6875, 330.01953125000006)});

    public static LineString pathToStair = factory.createLineString(new Coordinate[]{
            new Coordinate(412.10937500000006, 327.08984375000006),
            new Coordinate(412.10937500000006, 449.16015625000006),
            new Coordinate(473.63281250000006, 449.16015625000006),
            new Coordinate(473.63281250000006, 569.2773437500001),
            new Coordinate(344.72656250000006, 569.2773437500001),
            new Coordinate(344.72656250000006, 486.2695312500001),
    });

    public static Polygon kitchenAndRestZone = factory.createPolygon(factory.createLinearRing(new Coordinate[]{
            new Coordinate(14.6484375, 420.83984375000006),
            new Coordinate(302.734375, 420.83984375000006),
            new Coordinate(302.734375, 606.3867187500001),
            new Coordinate(14.6484375, 606.3867187500001),
            new Coordinate(14.6484375, 420.83984375000006),
    }));

    public static MultiPolygon managerOffices = factory.createMultiPolygon(new Polygon[]{
            factory.createPolygon(factory.createLinearRing(new Coordinate[]{
                    new Coordinate(795.8984375000002, 373.96484375000006),
                    new Coordinate(984.3750000000002, 373.96484375000006),
                    new Coordinate(984.3750000000002, 604.4335937500001),
                    new Coordinate(795.8984375000002, 604.4335937500001),
                    new Coordinate(795.8984375000002, 373.96484375000006),
            })),
            factory.createPolygon(factory.createLinearRing(new Coordinate[]{
                    new Coordinate(170.8984375, 15.56640625),
                    new Coordinate(286.1328125, 15.56640625),
                    new Coordinate(286.1328125, 249.94140625),
                    new Coordinate(170.8984375, 249.94140625),
                    new Coordinate(170.8984375, 15.56640625),
            })),
    });

    private OfficePlanUtils() {
    }
}
