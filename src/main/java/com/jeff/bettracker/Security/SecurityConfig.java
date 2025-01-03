package com.jeff.bettracker.Security;

import com.jeff.bettracker.views.LoginView;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);

        // Setze die Weiterleitung nach erfolgreichem Login
        http
                .formLogin(form -> form
                        .loginPage("/login")  // Setzt die Login-Page, falls du eine benutzerdefinierte Login-Seite verwendest
                        .successHandler(authenticationSuccessHandler()) // Setze den eigenen Erfolgs-Handler
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true) // Session invalidieren
                        .deleteCookies("JSESSIONID") // Cookie löschen
                );
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails user =
                User.withUsername("user")
                        .password("{noop}user")
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build();
        return  new InMemoryUserDetailsManager(user, admin);
    }

    // Definiere den AuthenticationSuccessHandler
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Leite den Benutzer nach erfolgreichem Login zur Dashboard-Route weiter
            response.sendRedirect("/dashboard");
        };
    }

}
