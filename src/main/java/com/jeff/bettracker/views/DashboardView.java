package com.jeff.bettracker.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "dashboard")
@RolesAllowed({"ADMIN", "USER"})
public class DashboardView extends VerticalLayout {
    public DashboardView() {
        System.out.println("DashboardView created");

        Button button = new Button("Click me",
                e -> UI.getCurrent().navigate("login"));

        add(
                new H1("Dashboard View"),
                button

        );

    }

}
