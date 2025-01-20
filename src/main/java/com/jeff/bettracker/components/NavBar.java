package com.jeff.bettracker.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class NavBar extends HorizontalLayout {
    public NavBar() {
        // Navigation Buttons
        Button dashboardButton = new Button("Dashbord", e -> UI.getCurrent().navigate("dashboard"));
        Button adminButton = new Button("Admin", e -> UI.getCurrent().navigate("admin"));
        Button contactButton = new Button("Contact", e -> UI.getCurrent().navigate("contact"));

        // Prüfen, ob der Benutzer die Rolle "ADMIN" hat
        if (!hasRole("ADMIN")) {
            adminButton.setVisible(false); // Admin-Button ausblenden, wenn Rolle nicht vorhanden
        }

        // Avatar mit Dropdown-Menü
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("**********auth***********: " + auth);

        System.out.println("currentUsername: " + currentUsername);

        Logger logger = LoggerFactory.getLogger(NavBar.class);
        logger.info("currentUsername: " + currentUsername);
        Avatar avatar = new Avatar(currentUsername != null ? currentUsername : "Guest");
        //avatar.setImage("https://randomuser.me/api/portraits");
        ContextMenu contextMenu = new ContextMenu(avatar);
        contextMenu.setOpenOnClick(true);
        contextMenu.addItem("Profile", e -> UI.getCurrent().navigate("profile"));
        contextMenu.addItem("Settings", e -> UI.getCurrent().navigate("settings"));
        contextMenu.addItem("Logout", e ->{
            VaadinSession.getCurrent().getSession().invalidate();
            //UI.getCurrent().navigate("login");
        });

        // Styling und Layout
        setWidthFull();
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        add(dashboardButton, adminButton, contactButton, avatar);
    }

    public void logout() {
        //  UI.getCurrent().getSession().setAttribute(SecurityContextHolder.getContext().getAuthentication().getName(), null);
        UI.getCurrent().getSession().close();
        UI.getCurrent().navigate("login");
    }

    private boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_" + role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
