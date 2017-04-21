package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class NBPCurrencyService {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		springPIDAppRun(args, NBPCurrencyService.class);
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
