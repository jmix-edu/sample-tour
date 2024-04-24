package com.company.tour.view.sample.projection.wgsprojection;


import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "wgs-projection-sample-view", layout = MainView.class)
@ViewController("WgsProjectionSampleView")
@ViewDescriptor("wgs-projection-sample-view.xml")
public class WgsProjectionSampleView extends StandardView {
}