package com.gemengserv.bff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class SwaggerAggregator {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SwaggerRoutes swaggerRoutes;

    // This handles the DROPDOWN menu configuration
    // This MUST match 'config-url' in your YAML
    @GetMapping("/v3/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig() {
        List<Map<String, String>> uiUrls = new ArrayList<>();

        // We build the list of apps for the dropdown
        swaggerRoutes.getRoutes().forEach((name, port) -> {
            uiUrls.add(Map.of(
                    "name", name.toUpperCase(),
                    "url", "/" + name + "/v3/api-docs" // This path must exist below
            ));
        });

        return Map.of(
                "configUrl", "/v3/api-docs/swagger-config",
                "urls", uiUrls
        );
    }

    // This handles fetching the ACTUAL JSON from your microservices
    @GetMapping("/{appName}/v3/api-docs")
    public Object getApiDocs(@PathVariable String appName) {
        String port = swaggerRoutes.getRoutes().get(appName.toLowerCase());

        if (port == null) {
            return "Error: App " + appName + " not found in YAML routes";
        }

        // BFF calls the microservice on its v3 endpoint
        String url = "http://localhost:" + port + "/v3/api-docs";
        return restTemplate.getForObject(url, Object.class);
    }
}