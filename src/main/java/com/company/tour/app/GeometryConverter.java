package com.company.tour.app;

import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;

import java.util.HashMap;
import java.util.Map;

public final class GeometryConverter {

    public static final String CRS_3035 = "EPSG:3035";
    public static final String CRS_4326 = "EPSG:4326";

    private static final Map<String, CoordinateReferenceSystem> CRSs = new HashMap<>();

    public static final String OGC_WKT_3035 = """
            PROJCS["ETRS89-extended / LAEA Europe",
                GEOGCS["ETRS89",
                    DATUM["European_Terrestrial_Reference_System_1989",
                        SPHEROID["GRS 1980",6378137,298.257222101,
                            AUTHORITY["EPSG","7019"]],
                        TOWGS84[0,0,0,0,0,0,0],
                        AUTHORITY["EPSG","6258"]],
                    PRIMEM["Greenwich",0,
                        AUTHORITY["EPSG","8901"]],
                    UNIT["degree",0.0174532925199433,
                        AUTHORITY["EPSG","9122"]],
                    AUTHORITY["EPSG","4258"]],
                PROJECTION["Lambert_Azimuthal_Equal_Area"],
                PARAMETER["latitude_of_center",52],
                PARAMETER["longitude_of_center",10],
                PARAMETER["false_easting",4321000],
                PARAMETER["false_northing",3210000],
                UNIT["metre",1,
                    AUTHORITY["EPSG","9001"]],
                AUTHORITY["EPSG","3035"]]
            """;

    static {
        CRSs.put(CRS_4326, DefaultGeographicCRS.WGS84);
        try {
            CoordinateReferenceSystem crs3035 = CRS.parseWKT(OGC_WKT_3035);
            CRSs.put(CRS_3035, crs3035);
        } catch (FactoryException e) {
            throw new IllegalStateException("Cannot parse CRS from WKT value", e);
        }
    }

    private GeometryConverter() {
    }

    public static <T extends Geometry> T convertFromWGS(Geometry source, String targetCrsName) {
        return convert(source, CRS_4326, targetCrsName);
    }

    public static <T extends Geometry> T convert(Geometry source, String sourceCrsName, String targetCrsName) {
        try {
            MathTransform transform = CRS.findMathTransform(CRSs.get(sourceCrsName), CRSs.get(targetCrsName));
            return (T) JTS.transform(source, transform);
        } catch (FactoryException e) {
            throw new IllegalStateException("No math transform can be created for the specified source and target CRS", e);
        } catch (TransformException e) {
            throw new IllegalStateException("Cannot transform", e);
        }
    }
}
