package pl.java.scalatech;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync 
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class UserApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    
	public UserApplication(DiscoveryClient discoveryClient) {
        super();
        this.discoveryClient = discoveryClient;
    }

    public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
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
