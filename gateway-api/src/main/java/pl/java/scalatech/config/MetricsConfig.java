package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
// @EnableMetrics
public class MetricsConfig {

	@Bean
	MetricRegistry metricRegistry() {
		return new MetricRegistry();
	}

	@Bean
	public HealthCheckRegistry healthChecks() {
		HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();
		// final ConnectionHealthCheck connectionHealthCheck = new
		// ConnectionHealthCheck(customerServiceURI, restTemplate);
		// healthCh eckRegistry.register("customers/repository",
		// connectionHealthCheck);
		return healthCheckRegistry;
	}

}