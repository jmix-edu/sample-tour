package com.company.tour.view.sample.projection.reprojection;


import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "reprojection-sample-view", layout = MainView.class)
@ViewController("ReprojectionSampleView")
@ViewDescriptor("reprojection-sample-view.xml")
public class ReprojectionSampleView extends StandardView {
}