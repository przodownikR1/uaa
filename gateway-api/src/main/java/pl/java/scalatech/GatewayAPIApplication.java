package pl.java.scalatech;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrix
@EnableEurekaClient
public class GatewayAPIApplication {

	public GatewayAPIApplication(DiscoveryClient discoveryClient) {
		super();
		this.discoveryClient = discoveryClient;
	}

	public static void main(String[] args) {
		springPIDAppRun(args, GatewayAPIApplication.class);
	}

	private static void springPIDAppRun(String[] args, Class<?> clazz) {
		SpringApplication springApplication = new SpringApplication(clazz);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
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