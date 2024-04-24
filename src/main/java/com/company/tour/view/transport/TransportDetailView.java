package com.company.tour.view.transport;

import com.company.tour.entity.Transport;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "transports/:id", layout = MainView.class)
@ViewController("Transport.detail")
@ViewDescriptor("transport-detail-view.xml")
@EditedEntityContainer("transportDc")
public class TransportDetailView extends StandardDetailView<Transport> {
}