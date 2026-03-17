package com.gemengserv.bff.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL; // This will show the request and response body
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new MyFeignErrorDecoder();
    }
}
