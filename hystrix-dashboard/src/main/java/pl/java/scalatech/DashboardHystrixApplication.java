package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.strategy.properties.HystrixPropertiesFactory;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient
@RefreshScope
@Controller
@Slf4j
public class DashboardHystrixApplication {

	private static final String HYSTRIX = "hystrix";

	@RequestMapping("/")
	String home() {
		return "forward:/hystrix";
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		springPIDAppRun(args, DashboardHystrixApplication.class);
	}

	private static void springPIDAppRun(String[] args, Class<?> clazz) {
		SpringApplication springApplication = new SpringApplication(clazz);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Bean
	ServletRegistrationBean mockStreamServlet() {
		return new ServletRegistrationBean(new MockStreamServlet(), "/mock.stream");
	}

	@Bean
	ApplicationListener<EnvironmentChangeEvent> listener() {
		return event -> {
			for (String key : event.getKeys()) {
				if (key.startsWith(HYSTRIX)) {
					HystrixPropertiesFactory.reset();
					log.info("hystrix properties has changed, event:{}", event.toString());
					break;
				}
			}

		};
	}
}
