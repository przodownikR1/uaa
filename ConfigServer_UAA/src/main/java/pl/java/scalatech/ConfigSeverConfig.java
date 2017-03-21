package pl.java.scalatech;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableRetry
public class ConfigSeverConfig {

}
