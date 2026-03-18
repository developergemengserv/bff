package com.gemengserv.bff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class SwaggerAggregator {

    @Autowired
    private RestTemplate restTemplate;

    // 1. FETCH THE ACTUAL DOCS FROM MICROSERVICES
    @GetMapping("/{appName}/v2/api-docs")
    public String getApiDocs(@PathVariable String appName) {
        String targetPort = appName.equalsIgnoreCase("tejraj") ? "8081" : "8082";
        String url = "http://localhost:" + targetPort + "/v2/api-docs";

        // This fetches the JSON from the microservice and returns it to your browser on 9090
        return restTemplate.getForObject(url, String.class);
    }

    // 2. FIX THE "REMOTE CONFIGURATION" ERROR
    // Swagger UI calls this first to build the UI layout
    @GetMapping("/v2/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig() {
        return Map.of(
                "configUrl", "/v2/api-docs/swagger-config",
                "urls", List.of(
                        Map.of("name", "Tejraj App", "url", "/tejraj/v2/api-docs"),
                        Map.of("name", "Ashwinsheth App", "url", "/ashwinsheth/v2/api-docs")
                )
        );
    }
}