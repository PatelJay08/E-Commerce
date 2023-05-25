package com.jayptl08.microservice.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/user-service/**")
                        .uri("lb://user-service"))
                .route(p -> p.path("/product-catalog-service/**")
                        .uri("lb://product-catalog-service"))
                .route(p -> p.path("/order-service/**")
                        .uri("lb://order-service"))
                .build();
    }

}
