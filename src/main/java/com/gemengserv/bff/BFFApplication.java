package com.gemengserv.bff;

import com.gemengserv.bff.config.SwaggerAggregator;
import com.gemengserv.bff.config.SwaggerRoutes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gemengserv.bff")
@EnableConfigurationProperties(SwaggerRoutes.class)
@EnableSwagger2
public class BFFApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BFFApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gemengserv.bff"))
				.paths(PathSelectors.any())
				.build();
	}

}
