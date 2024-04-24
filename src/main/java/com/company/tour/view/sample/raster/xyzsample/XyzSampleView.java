package com.company.tour.view.sample.raster.xyzsample;

import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "xyz-sample-view", layout = MainView.class)
@ViewController("XyzSampleView")
@ViewDescriptor("xyz-sample-view.xml")
public class XyzSampleView extends StandardView {
}