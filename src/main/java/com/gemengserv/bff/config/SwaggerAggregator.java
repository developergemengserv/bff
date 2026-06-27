package com.gemengserv.bff.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SwaggerAggregator {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SwaggerRoutes swaggerRoutes;

    // 1. Handles the DROPDOWN menu configuration
    @GetMapping("/v3/api-docs/swagger-config")
    public Map<String, Object> swaggerConfig() {
        List<Map<String, String>> uiUrls = new ArrayList<>();

        swaggerRoutes.getRoutes().forEach((name, port) -> {
            uiUrls.add(Map.of(
                    "name", name.toUpperCase(),
                    "url", "/" + name + "/v3/api-docs"
            ));
        });

        return Map.of(
                "configUrl", "/v3/api-docs/swagger-config",
                "urls", uiUrls
        );
    }

    // 2. Handles fetching the ACTUAL JSON OpenAPI specs from your microservices
    @GetMapping("/{appName}/v3/api-docs")
    public Object getApiDocs(@PathVariable String appName) {
        String port = swaggerRoutes.getRoutes().get(appName.toLowerCase());

        if (port == null) {
            return "Error: App " + appName + " not found in YAML routes";
        }

        String url = "http://localhost:" + port + "/v3/api-docs";
        return restTemplate.getForObject(url, Object.class);
    }

    // 3. ADDED: Catch-all business API proxy router
    // This intercepts actual requests (e.g. /tejraj/getEmergencyHelpline) and forwards them seamlessly
    // 3. Updated Catch-all business API proxy router
    // The regex constraint prevents it from hijacking internal swagger assets
    @RequestMapping(
            value = "/{appName:(?!swagger-ui|v3|webjars|swagger-resources)[a-zA-Z0-9_-]+}/**",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
    )
    public ResponseEntity<byte[]> proxyBusinessRequests(
            @PathVariable String appName,
            HttpServletRequest request,
            @RequestBody(required = false) byte[] body) {

        String port = swaggerRoutes.getRoutes().get(appName.toLowerCase());
        if (port == null) {
            return ResponseEntity.notFound().build();
        }

        // Reconstruct path and query strings safely
        String fullPath = request.getRequestURI();
        String queryString = request.getQueryString();
        String targetUrl = "http://localhost:" + port + fullPath + (queryString != null ? "?" + queryString : "");

        // Forward headers (auth tokens, userIds, content-type, etc.)
        HttpHeaders headers = new HttpHeaders();
        Collections.list(request.getHeaderNames()).forEach(headerName ->
                headers.add(headerName, request.getHeader(headerName))
        );

        HttpEntity<byte[]> entity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.exchange(targetUrl, HttpMethod.valueOf(request.getMethod()), entity, byte[].class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body(("Gateway connection failed routing to port " + port).getBytes());
        }
    }
}
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//@RestController
//public class SwaggerAggregator {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private SwaggerRoutes swaggerRoutes;
//
//    // This handles the DROPDOWN menu configuration
//    // This MUST match 'config-url' in your YAML
//    @GetMapping("/v3/api-docs/swagger-config")
//    public Map<String, Object> swaggerConfig() {
//        List<Map<String, String>> uiUrls = new ArrayList<>();
//
//        // We build the list of apps for the dropdown
//        swaggerRoutes.getRoutes().forEach((name, port) -> {
//            uiUrls.add(Map.of(
//                    "name", name.toUpperCase(),
//                    "url", "/" + name + "/v3/api-docs" // This path must exist below
//            ));
//        });
//
//        return Map.of(
//                "configUrl", "/v3/api-docs/swagger-config",
//                "urls", uiUrls
//        );
//    }
//
//    // This handles fetching the ACTUAL JSON from your microservices
//    @GetMapping("/{appName}/v3/api-docs")
//    public Object getApiDocs(@PathVariable String appName) {
//        String port = swaggerRoutes.getRoutes().get(appName.toLowerCase());
//
//        if (port == null) {
//            return "Error: App " + appName + " not found in YAML routes";
//        }
//
//        // BFF calls the microservice on its v3 endpoint
//        String url = "http://localhost:" + port + "/v3/api-docs";
//        return restTemplate.getForObject(url, Object.class);
//    }
//}*/
