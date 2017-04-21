package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@SpringBootApplication
@EnableAsync
@EnableCircuitBreaker
@EnableEurekaClient
public class CarApplication {

	public static void main(String[] args) {
		springPIDAppRun(args, CarApplication.class);
	}

	private static void springPIDAppRun(String[] args, Class<?> clazz) {
		SpringApplication springApplication = new SpringApplication(clazz);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Bean
	FilterRegistrationBean userInsertingMdcFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		MDCInsertingServletFilter userFilter = new MDCInsertingServletFilter();
		registrationBean.setFilter(userFilter);
		return registrationBean;
	}
}
