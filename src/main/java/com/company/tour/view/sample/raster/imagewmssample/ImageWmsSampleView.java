package com.company.tour.view.sample.raster.imagewmssample;


import com.company.tour.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "image-wms-sample-view", layout = MainView.class)
@ViewController("ImageWmsSampleView")
@ViewDescriptor("image-wms-sample-view.xml")
public class ImageWmsSampleView extends StandardView {
}