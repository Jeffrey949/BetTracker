package com.jeff.bettracker.views;

import com.jeff.bettracker.backend.User;
import com.jeff.bettracker.components.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route(value = "admin", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {
    public AdminView() {
        var crud = new GridCrud<>(User.class);
        //crud.getGrid().setColumns("name", "role");
        add(
                new H1("Admin View"),
                crud
        );
    }
}
