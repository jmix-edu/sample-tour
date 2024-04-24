package com.company.tour.view.stop;

import com.company.tour.entity.Stop;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "stops", layout = MainView.class)
@ViewController("Stop.list")
@ViewDescriptor("stop-list-view.xml")
@LookupComponent("stopsDataGrid")
@DialogMode(width = "64em")
public class StopListView extends StandardListView<Stop> {
}