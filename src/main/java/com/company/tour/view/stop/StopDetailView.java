package com.company.tour.view.stop;

import com.company.tour.entity.Stop;

import com.company.tour.view.main.MainView;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "stops/:id", layout = MainView.class)
@ViewController("Stop.detail")
@ViewDescriptor("stop-detail-view.xml")
@EditedEntityContainer("stopDc")
public class StopDetailView extends StandardDetailView<Stop> {
    @Autowired
    private DialogWindows dialogWindows;

    @Subscribe(id = "locationLookupBtn", subject = "clickListener")
    public void onLocationLookupBtnClick(final ClickEvent<JmixButton> event) {
        dialogWindows.view(this, StopLocationLookupView.class)
                .withViewConfigurer(view -> view.setItem(getEditedEntity()))
                .open();
    }
}