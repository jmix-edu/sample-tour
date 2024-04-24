package com.company.tour.view.stop;

import com.company.tour.entity.Stop;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "stops/:id", layout = MainView.class)
@ViewController("Stop.detail")
@ViewDescriptor("stop-detail-view.xml")
@EditedEntityContainer("stopDc")
public class StopDetailView extends StandardDetailView<Stop> {
}