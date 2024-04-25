package com.company.tour.view.sample.heatmap;

import com.company.tour.app.HeatmapUtils;
import com.company.tour.entity.City;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.model.layer.HeatmapLayer;
import io.jmix.mapsflowui.component.model.source.HeatmapDataVectorSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;

import java.math.BigDecimal;

@Route(value = "heatmap-sample-view", layout = MainView.class)
@ViewController("HeatmapSampleView")
@ViewDescriptor("heatmap-sample-view.xml")
public class HeatmapSampleView extends StandardView {
    @ViewComponent("map.heatmap.source")
    private VectorSource source;
    @ViewComponent("map.heatmap1.dataSource")
    private HeatmapDataVectorSource<City> dataSource;
    @ViewComponent("map.heatmap1")
    private HeatmapLayer mapHeatmap1;
    @ViewComponent("map.heatmap")
    private HeatmapLayer mapHeatmap;

    @Subscribe
    public void onInit(final InitEvent event) {
        source.addAllFeatures(HeatmapUtils.getFeatures());

        dataSource.setWeightProvider(city ->
                (BigDecimal.valueOf(city.getPopulation()).doubleValue() / HeatmapUtils.POP_MAX) * 10);

        mapHeatmap.setGradient(HeatmapUtils.GRADIENT);
        mapHeatmap1.setGradient(HeatmapUtils.GRADIENT);
    }
}