package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableZuulProxy    
@EnableDiscoveryClient
public class SpringOAuth2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringOAuth2Application.class, args);
	}
}
