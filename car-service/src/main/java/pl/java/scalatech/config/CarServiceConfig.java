package pl.java.scalatech.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@EnableAsync
@EnableCircuitBreaker
@EnableEurekaClient
@RefreshScope
@Slf4j
class CarServiceConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final DiscoveryClient discoveryClient;

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            List<ServiceInstance> instances = discoveryClient.getInstances("discovery-service");
            if (instances.size() > 0) {
                log.info("eureka instances :  {}", instances.get(0).getUri().toString());
            }
        };
    }
}
