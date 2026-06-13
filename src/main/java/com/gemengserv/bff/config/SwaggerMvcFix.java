package com.gemengserv.bff.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SwaggerMvcFix {

    @Bean
    public static BeanPostProcessor springfoxHandlerMethodMappingsOrderFix() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // Targets the exact class throwing the error in your stack trace
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                // Filters out any mapping using the modern PathPatternParser (like Actuator)
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    // Stripped reflection down to match Springfox 2.9.2 internal fields perfectly
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    if (field != null) {
                        field.setAccessible(true);
                        return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                    }
                    return java.util.Collections.emptyList();
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}