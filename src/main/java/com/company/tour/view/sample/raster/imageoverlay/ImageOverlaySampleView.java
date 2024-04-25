package com.company.tour.view.sample.raster.imageoverlay;


import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "image-overlay-sample-view", layout = MainView.class)
@ViewController("ImageOverlaySampleView")
@ViewDescriptor("image-overlay-sample-view.xml")
public class ImageOverlaySampleView extends StandardView {
}