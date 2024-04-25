package com.company.tour.view.sample.raster.osm;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "osm-sample-view", layout = MainView.class)
@ViewController("OsmSampleView")
@ViewDescriptor("osm-sample-view.xml")
public class OsmSampleView extends StandardView {
}