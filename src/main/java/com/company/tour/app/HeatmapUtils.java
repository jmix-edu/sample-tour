package com.company.tour.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import org.locationtech.jts.geom.Point;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HeatmapUtils {

    public static final Integer POP_MAX = 35676000;
    public static final Integer POP_MIN = 200;

    public static final List<String> GRADIENT = List.of("#30E3DF", "#FCE22A", "#F94A29", "#D61355");

    private static final List<Feature> features = new ArrayList<>(243);

    static {
        try (InputStream resourceAsStream = HeatmapUtils.class.
                getResourceAsStream("/com/company/tour/sampledata/ne_110m_populated_places.geojson")) {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonFeatures jsonFeatures = objectMapper.readValue(resourceAsStream, JsonFeatures.class);

            populateFeatures(jsonFeatures.getFeatures());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read JSON data for heatmap sample", e);
        }
    }

    public static List<Feature> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    private static void populateFeatures(List<JsonFeatures.JsonFeature> jsonFeatures) {
        features.addAll(jsonFeatures.stream()
                .map(json -> new PointFeature(createGeometry(json)))
                .toList());
    }

    private static Point createGeometry(JsonFeatures.JsonFeature json) {
        return GeometryUtils.createPoint(json.getGeometry().coordinates[0], json.getGeometry().coordinates[1]);
    }

    private static Double calculateIntensity(JsonFeatures.JsonFeature json) {
        int cityPopulation = (int) json.getProperties().getOrDefault("POP_MAX", POP_MIN);

        return (BigDecimal.valueOf(cityPopulation).doubleValue() / POP_MAX) * 10;
    }

    protected static class JsonFeatures {
        protected String type;
        protected List<JsonFeature> features;

        public List<JsonFeature> getFeatures() {
            return features;
        }

        public void setFeatures(List<JsonFeature> features) {
            this.features = features;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        protected static class JsonFeature {
            protected String type;
            protected Map<String, Object> properties;
            protected Geometry geometry;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Map<String, Object> getProperties() {
                return properties;
            }

            public void setProperties(Map<String, Object> properties) {
                this.properties = properties;
            }

            public Geometry getGeometry() {
                return geometry;
            }

            public void setGeometry(Geometry geometry) {
                this.geometry = geometry;
            }

            protected static class Geometry {
                protected String type;
                protected Double[] coordinates;

                public Double[] getCoordinates() {
                    return coordinates;
                }

                public void setCoordinates(Double[] coordinates) {
                    this.coordinates = coordinates;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
