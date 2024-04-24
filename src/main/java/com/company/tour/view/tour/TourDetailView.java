package com.company.tour.view.tour;

import com.company.tour.entity.Tour;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "tours/:id", layout = MainView.class)
@ViewController("Tour.detail")
@ViewDescriptor("tour-detail-view.xml")
@EditedEntityContainer("tourDc")
public class TourDetailView extends StandardDetailView<Tour> {
}