package com.company.tour.view.stop;


import com.company.tour.entity.Stop;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.event.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "stop-location-lookup-view", layout = MainView.class)
@ViewController("StopLocationLookupView")
@ViewDescriptor("stop-location-lookup-view.xml")
@DialogMode(width = "60em", height = "40em")
public class StopLocationLookupView extends StandardView {
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private InstanceContainer<Stop> stopDc;

    public void setItem(Stop stop) {
        stopDc.setItem(stop);
    }

    public Stop getItem() {
        return stopDc.getItem();
    }

    @Subscribe("map")
    public void onMapMapClick(final MapClickEvent event) {
        stopDc.getItem().setLocation(GeometryUtils.createPoint(
                event.getCoordinate().getX(),
                event.getCoordinate().getY()));
    }

    @Subscribe("map")
    public void onMapMapSingleClick(final MapSingleClickEvent event) {
        notifications.create("MapSingleClickEvent", event.getCoordinate() + "")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("map")
    public void onMapMapDoubleClick(final MapDoubleClickEvent event) {
        notifications.create("MapDoubleClickEvent", event.getCoordinate() + "")
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("map")
    public void onMapMapMoveEnd(final MapMoveEndEvent event) {
        notifications.create("MapMoveEndEvent", "Center: " + event.getCenter())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }

    @Subscribe("map")
    public void onMapMapZoomChanged(final MapZoomChangedEvent event) {
        notifications.create("MapZoomChangedEvent", "Zoom: " + event.getZoom())
                .withPosition(Notification.Position.BOTTOM_END)
                .show();
    }


}