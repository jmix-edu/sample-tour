package com.company.tour.view.sample.raster.wmssample;

import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "wms-sample-view", layout = MainView.class)
@ViewController("WmsSampleView")
@ViewDescriptor("wms-sample-view.xml")
public class WmsSampleView extends StandardView {
}