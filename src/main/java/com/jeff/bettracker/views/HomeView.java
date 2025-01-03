package com.jeff.bettracker.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(new Span("Welcome to the Home View!"));

        Button button = new Button("Click me",
                e -> UI.getCurrent().navigate("login"));

    }
}
