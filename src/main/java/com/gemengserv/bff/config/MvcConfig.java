package com.gemengserv.bff.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.Set;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Register all your frontend sub-folder names here
    private static final Set<String> SPA_FOLDERS = Set.of(
            "notandasfe",
            "lvm",
            "hccfe"
    );

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);

                        // 1. If the exact file exists (bundle.js, style.css, images, etc.), serve it
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        }

                        // 2. Ignore backend API endpoints
                        if (resourcePath.startsWith("api/") || resourcePath.startsWith("api")) {
                            return null;
                        }

                        // 3. Extract the first path segment to find the target app
                        // e.g. "notandasfe/dashboard/overview" -> "notandasfe"
//                        String appFolder = resourcePath.split("/")[0];
//
//                        if (SPA_FOLDERS.contains(appFolder)) {
//                            Resource appIndex = new ClassPathResource("/static/" + appFolder + "/index.html");
//                            if (appIndex.exists() && appIndex.isReadable()) {
//                                return appIndex;
//                            }
//                        }

                        // 3. Extract the root subfolder (e.g., "notandasfe/dashboard" -> "notandasfe")
                        int slashIndex = resourcePath.indexOf('/');
                        String appFolder = (slashIndex != -1) ? resourcePath.substring(0, slashIndex) : resourcePath;

                        // 4. Dynamically test if "/static/{appFolder}/index.html" exists
                        Resource spaIndex = new ClassPathResource("/static/" + appFolder + "/index.html");
                        if (spaIndex.exists() && spaIndex.isReadable()) {
                            return spaIndex;
                        }

                        // Not a recognized SPA route or file
                        return null;
                    }
                });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }
}
