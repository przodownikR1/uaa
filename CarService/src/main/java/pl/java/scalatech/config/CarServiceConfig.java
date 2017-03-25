package pl.java.scalatech.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
class CarServiceConfig {

	  private final DiscoveryClient discoveryClient;

	    @Bean
	    CommandLineRunner commandLineRunner() {
	        return (args) -> {
	            List<ServiceInstance> instances = discoveryClient.getInstances("discovery-service");
	            if (instances.size() > 0) {
	                log.info("eureka instances :  {}",instances.get(0).getUri().toString());
	            }
	        };
	    }
}
