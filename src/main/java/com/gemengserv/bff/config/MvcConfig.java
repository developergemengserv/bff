package com.gemengserv.bff.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. ADDED LEADING SLASH: Map Swagger 2's HTML entry portal explicitly
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // 2. Map Swagger 2's internal JavaScript and CSS engine modules
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 3. Catch-all rule for your local frontend / regular static files
        // CRITICAL: We move this to the bottom so it doesn't hijack the Swagger resources above!
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Optional: Automatically redirects root url requests (/) over to the Swagger landing page
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
    }
}