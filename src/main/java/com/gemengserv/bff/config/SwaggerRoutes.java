package com.gemengserv.bff.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "app-routes")
public class SwaggerRoutes {
    private Map<String, String> routes = new HashMap<>();

    public Map<String, String> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, String> routes) {
        System.out.println("--- SWAGGER ROUTES LOADED: " + routes + " ---");
        this.routes = routes;
    }

    @javax.annotation.PostConstruct
    public void init() {
        System.out.println(">>> SwaggerRoutes Bean Initialized. Map size: " + routes.size());
    }
}