package com.company.tour.view.citydistrict;

import com.company.tour.entity.CityDistrict;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "cityDistricts/:id", layout = MainView.class)
@ViewController("CityDistrict.detail")
@ViewDescriptor("city-district-detail-view.xml")
@EditedEntityContainer("cityDistrictDc")
public class CityDistrictDetailView extends StandardDetailView<CityDistrict> {
}