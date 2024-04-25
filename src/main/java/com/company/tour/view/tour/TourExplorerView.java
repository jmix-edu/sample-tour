package com.company.tour.view.tour;


import com.company.tour.DemoDataInitializer;
import com.company.tour.app.TourExplorerUtils;
import com.company.tour.entity.*;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import io.jmix.maps.utils.GeometryUtils;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.apache.commons.collections4.CollectionUtils;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.context.event.EventListener;

import java.util.List;

@Route(value = "tour-explorer-view", layout = MainView.class)
@ViewController("TourExplorerView")
@ViewDescriptor("tour-explorer-view.xml")
public class TourExplorerView extends StandardView {
    @ViewComponent
    private CollectionContainer<City> citiesDc;
    @ViewComponent
    private CollectionContainer<Transport> transportsDc;
    @ViewComponent
    private EntityComboBox<City> citiesComboBox;

    @ViewComponent("map.districtsLayer.districtsSource")
    private DataVectorSource<CityDistrict> districtsSource;
    @ViewComponent("map.toursLayer.toursSource")
    private DataVectorSource<Tour> toursSource;
    @ViewComponent("map.stopsLayer.stopsSource")
    private DataVectorSource<Stop> stopsSource;
    @ViewComponent("map.transportsLayer.transportsSource")
    private DataVectorSource<Transport> transportsSource;
    @ViewComponent
    private GeoMap map;


    @Subscribe
    public void onInit(final InitEvent event) {
        map.setProjection(() -> "EPSG:3035");

        districtsSource.setStyleProvider(district -> new Style()
                .withFill(new Fill(TourExplorerUtils.getDistrictFillColor(district.getName())))
                .withStroke(new Stroke()
                        .withColor(TourExplorerUtils.getDistrictStrokeColor(district.getName()))
                        .withWidth(2d)));
        toursSource.setStyleProvider(tour -> new Style()
                .withStroke(new Stroke()
                        .withWidth(6d)
                        .withLineDash(List.of(15d, 10d))
                        .withColor(tour.getRouteColor())));
        stopsSource.setStyleProvider(stop -> new Style()
                .withImage(new IconStyle()
                        .withScale(0.05)
                        .withSrc("icons/map/sight-icon.png")
                        .withAnchor(new Anchor(0.5, 1.15)))
                .withText(new TextStyle()
                        .withOffsetY(10)
                        .withText(stop.getName())
                        .withFont("bold 14px sans-serif")));

        stopsSource.addGeoObjectClickListener(clickEvent -> {
            map.setCenter(clickEvent.getItem().getLocation3035().getCoordinate());
            map.setZoom(15);
        });

        transportsSource.setStyleProvider(transport -> {
            String iconPath;
            switch (transport.getType()) {
                case BUS -> iconPath = "icons/map/bus.png";
                case MINIVAN -> iconPath = "icons/map/minivan.png";
                default -> iconPath = "icons/map/car.png";
            }
            return new Style()
                    .withImage(new IconStyle()
                            .withScale(0.05)
                            .withSrc(iconPath));
        });
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (CollectionUtils.isNotEmpty(citiesDc.getItems())) {
            citiesComboBox.setValue(TourExplorerUtils.getBerlinCityFrom(citiesDc.getItems()));
        }

        transportsDc.getItems().forEach(t -> {
            if (t.getTour() == null) {
                return;
            }

            Coordinate coordinate = null;
            switch (t.getTour().getName()) {
                case TourExplorerUtils.BIG_GARDEN_TOUR ->
                        coordinate = TourExplorerUtils.BIG_GARDEN_TOUR_COORDINATES.get(0);
                case TourExplorerUtils.BERLIN_CENTER_TOUR ->
                        coordinate = TourExplorerUtils.BERLIN_CENTER_TOUR_COORDINATES.get(0);
                case TourExplorerUtils.FERNSEHTURM_TOUR ->
                        coordinate = TourExplorerUtils.FERNSEHTURM_TOUR_COORDINATES.get(0);
            }
            if (coordinate != null) {
                t.setLocation3035(GeometryUtils.createPoint(coordinate.getX(), coordinate.getY()));
            }
        });
    }

    @EventListener
    public void onTransportUpdate(DemoDataInitializer.TransportUpdateEvent event) {
        transportsDc.getItems().stream()
                .filter(t -> t.getId().equals(event.getTransportId()))
                .findFirst()
                .ifPresent(t -> t.setLocation3035(
                        event.getCoordinate() != null
                                ? GeometryUtils.createPoint(event.getX(), event.getY())
                                : null));
    }
}