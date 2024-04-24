package com.company.tour.view.transport;

import com.company.tour.entity.Transport;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "transports", layout = MainView.class)
@ViewController("Transport.list")
@ViewDescriptor("transport-list-view.xml")
@LookupComponent("transportsDataGrid")
@DialogMode(width = "64em")
public class TransportListView extends StandardListView<Transport> {
}