package com.gemengserv.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Attach the interceptor so it monitors all outgoing calls
        restTemplate.setInterceptors(Collections.singletonList(new LvmTokenInterceptor()));
        return restTemplate;    }
}
