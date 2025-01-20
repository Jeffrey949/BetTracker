package com.jeff.bettracker.components;

import com.vaadin.flow.component.applayout.AppLayout;

public class MainLayout extends AppLayout {
    public MainLayout() {
        //UI.getCurrent().getElement().getThemeList().add("dark");
        NavBar navBar = new NavBar();
        addToNavbar(navBar);
    }
}
