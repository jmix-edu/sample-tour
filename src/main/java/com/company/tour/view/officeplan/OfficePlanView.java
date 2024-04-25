package com.company.tour.view.officeplan;


import com.company.tour.app.OfficePlanUtils;
import com.company.tour.entity.User;
import com.company.tour.view.main.MainView;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.core.FileStorage;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.FitOptions;
import io.jmix.mapsflowui.component.model.feature.MultiPointFeature;
import io.jmix.mapsflowui.component.model.feature.MultiPolygonFeature;
import io.jmix.mapsflowui.component.model.feature.PointFeature;
import io.jmix.mapsflowui.component.model.feature.PolygonFeature;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.component.model.source.VectorSource;
import io.jmix.mapsflowui.kit.component.model.Easing;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.MarkerStyle;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "office-plan-view", layout = MainView.class)
@ViewController("OfficePlanView")
@ViewDescriptor("office-plan-view.xml")
public class OfficePlanView extends StandardView {
    @ViewComponent("map.vector")
    private VectorLayer vector;
    @ViewComponent("map.vector.source")
    private VectorSource source;
    @ViewComponent("map.dataVector.dataSource")
    private DataVectorSource<User> dataSource;
    @Autowired
    private FileStorage fileStorage;
    @ViewComponent
    private GeoMap map;

    @Subscribe
    public void onInit(final InitEvent event) {
        Fill fill = new Fill(OfficePlanUtils.VECTOR_FILL_COLOR);
        Stroke stroke = new Stroke()
                .withColor(OfficePlanUtils.VECTOR_STROKE_COLOR)
                .withWidth(4d);

        vector.addStyles(new Style()
                .withFill(fill)
                .withStroke(stroke)
                .withImage(new CircleStyle()
                        .withFill(fill)
                        .withStroke(stroke)
                        .withRadius(6)));

        dataSource.setStyleProvider(user -> {
            if (user.getAvatar() == null) {
                return MarkerStyle.createDefaultStyle();
            }
            return new Style()
                    .withImage(new IconStyle()
                            .withAnchor(new Anchor(0.5, 1.2))
                            .withScale(0.08)
                            .withResource(new StreamResource(user.getAvatar().getFileName(),
                                    () -> fileStorage.openStream(user.getAvatar()))))
                    .withText(new TextStyle()
                            .withText(user.getFirstName() + " " + user.getLastName())
                            .withFont("bold 14px sans-serif"));
        });

        source.addFeature(new PointFeature(OfficePlanUtils.stairPoint)
                .withStyles(new Style()
                        .withImage(new IconStyle()
                                .withSrc("icons/map/stair-icon.png")
                                .withScale(0.05))));
        source.addFeature(new MultiPointFeature(OfficePlanUtils.freeSitsPoint)
                .withStyles(new Style()
                        .withImage(new IconStyle()
                                .withSrc("icons/map/chair-icon.png")
                                .withScale(0.05))));
        PolygonFeature polygonFeature = new PolygonFeature(OfficePlanUtils.kitchenAndRestZone);
        polygonFeature.addClickListener(clickEvent -> map.fit(new FitOptions(polygonFeature)
                .withEasing(Easing.LINEAR)
                .withDuration(2000)));
        source.addFeature(polygonFeature);

        source.addFeature(new MultiPolygonFeature(OfficePlanUtils.managerOffices)
                .withStyles(new Style()
                        .withFill(new Fill(OfficePlanUtils.MULTI_POLYGON_FILL_COLOR))
                        .withStroke(new Stroke()
                                .withWidth(5d)
                                .withColor(OfficePlanUtils.MULTI_POLYGON_STROKE_COLOR))));
    }
}