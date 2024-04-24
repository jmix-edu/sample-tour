package com.company.tour.view.sample.cluster;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "cluster-sample-view", layout = MainView.class)
@ViewController("ClusterSampleView")
@ViewDescriptor("cluster-sample-view.xml")
public class ClusterSampleView extends StandardView {
    @ViewComponent("map.vector.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(generatePoints());
    }

    private List<Feature> generatePoints() {
        List<Feature> features = new ArrayList<>(1000);
        int e = 55;
        for (int i = 0; i < 1000; i++) {
            Point point = GeometryUtils.createPoint(2 * e * Math.random(), 2 * e * Math.random() - e);
            features.add(new PointFeature(point));
        }
        return Collections.unmodifiableList(features);
    }
}