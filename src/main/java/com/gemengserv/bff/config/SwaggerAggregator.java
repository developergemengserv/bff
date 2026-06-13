package com.gemengserv.bff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary // Tells Springfox to use this provider instead of its default single-app provider
public class SwaggerAggregator implements SwaggerResourcesProvider {

    @Autowired
    private SwaggerRoutes swaggerRoutes;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        // Loop through your YAML routes and register them cleanly with Springfox
        swaggerRoutes.getRoutes().forEach((name, port) -> {
            resources.add(swaggerResource(name.toUpperCase(), "/" + name + "/v2/api-docs"));
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location); // This maps the proxy route handled by gateway/BFF
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}