package com.jeff.bettracker.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView  extends Composite<LoginOverlay> {
    public LoginView() {
        getContent().setAction("login");
        getContent().setOpened(true);
        getContent().setTitle("Vaadin CRM");
        getContent().setDescription("Example of a Vaadin CRM application");
        getContent().setForgotPasswordButtonVisible(false);
    }
}
