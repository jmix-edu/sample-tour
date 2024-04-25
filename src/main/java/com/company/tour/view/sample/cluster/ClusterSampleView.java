package com.company.tour.view.sample.cluster;

import com.company.tour.app.ClusterSampleUtils;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.source.ClusterSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.feature.Feature;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route(value = "cluster-sample-view", layout = MainView.class)
@ViewController("ClusterSampleView")
@ViewDescriptor("cluster-sample-view.xml")
public class ClusterSampleView extends StandardView {
    @ViewComponent("map.vector.cluster.source")
    private VectorSource source;
    @ViewComponent("map.vector.cluster")
    private ClusterSource cluster;

    @Subscribe
    public void onInit(final InitEvent event) {
        cluster.removeAllPointStyles();
        cluster.setPointTextStyle(new Style()
                .withText(new TextStyle()
                        .withOffsetY(1)
                        .withFill(new Fill(ClusterSampleUtils.CLUSTER_TEXT_COLOR))
                        .withFont("bold 12px sans-serif")));
        cluster.addPointStyles(
                new Style()
                        .withImage(new CircleStyle()
                                .withRadius(ClusterSampleUtils.CLUSTER_ICON_OUTER_RADIUS)
                                .withFill(new Fill(ClusterSampleUtils.CLUSTER_ICON_OUTER_COLOR))),
                new Style()
                        .withImage(new CircleStyle()
                                .withRadius(ClusterSampleUtils.CLUSTER_ICON_INNER_RADIUS)
                                .withFill(new Fill(ClusterSampleUtils.CLUSTER_ICON_INNER_COLOR))));

        source.addAllFeatures(generatePoints());
    }

    private List<Feature> generatePoints() {
        List<Feature> features = new ArrayList<>(1000);
        int e = 55;
        for (int i = 0; i < 1000; i++) {
            Point point = GeometryUtils.createPoint(2 * e * Math.random(), 2 * e * Math.random() - e);
            features.add(new PointFeature(point)
                    .withProperty("weight", Double.valueOf(Math.random() * 10).intValue()));
        }
        return Collections.unmodifiableList(features);
    }

    @Subscribe(id = "disableAtZoomBtn", subject = "clickListener")
    public void onDisableAtZoomBtnClick(final ClickEvent<JmixButton> event) {
        cluster.setDisableAtZoom(3d);
    }

    @Subscribe(id = "showSinglePointAsClusterBtn", subject = "clickListener")
    public void onShowSinglePointAsClusterBtnClick(final ClickEvent<JmixButton> event) {
        cluster.setShowSinglePointAsCluster(cluster.getShowSinglePointAsCluster() == null
                || !cluster.getShowSinglePointAsCluster());
    }

    @Subscribe(id = "distanceBtn", subject = "clickListener")
    public void onDistanceBtnClick(final ClickEvent<JmixButton> event) {
        cluster.setDistance(50);
    }
}