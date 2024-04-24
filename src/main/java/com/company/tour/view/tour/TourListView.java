package com.company.tour.view.tour;

import com.company.tour.entity.Tour;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "tours", layout = MainView.class)
@ViewController("Tour.list")
@ViewDescriptor("tour-list-view.xml")
@LookupComponent("toursDataGrid")
@DialogMode(width = "64em")
public class TourListView extends StandardListView<Tour> {
}