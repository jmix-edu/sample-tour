package com.company.tour.view.sample.heatmap;

import com.company.tour.app.HeatmapUtils;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.source.VectorSource;

@Route(value = "heatmap-sample-view", layout = MainView.class)
@ViewController("HeatmapSampleView")
@ViewDescriptor("heatmap-sample-view.xml")
public class HeatmapSampleView extends StandardView {
    @ViewComponent("map.vector.source")
    private VectorSource source;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(HeatmapUtils.getFeatures());
    }
}