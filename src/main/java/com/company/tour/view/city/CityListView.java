package com.company.tour.view.city;

import com.company.tour.entity.City;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cities", layout = MainView.class)
@ViewController("City.list")
@ViewDescriptor("city-list-view.xml")
@LookupComponent("citiesDataGrid")
@DialogMode(width = "64em")
public class CityListView extends StandardListView<City> {
}