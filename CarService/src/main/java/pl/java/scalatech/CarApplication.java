package pl.java.scalatech;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCircuitBreaker
@EnableEurekaClient
public class CarApplication {

    
    
	public CarApplication(DiscoveryClient discoveryClient) {
        super();
        this.discoveryClient = discoveryClient;
    }

    public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}
    private final DiscoveryClient discoveryClient;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            List<ServiceInstance> instances = discoveryClient.getInstances("discovery-service");
            if (instances.size() > 0) {
                System.out.println(instances.get(0).getUri().toString());
            }
        };
    }
}
