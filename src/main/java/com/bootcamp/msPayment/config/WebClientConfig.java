package com.bootcamp.msPayment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "client")
    @LoadBalanced
    public WebClient.Builder registrarWebClient() {
        return WebClient.builder();
    }
}
