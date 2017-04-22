package pl.java.scalatech.config;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.codahale.metrics.health.HealthCheck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConnectionHealthCheck extends HealthCheck {

	private final URI uri;
	private final RestTemplate restTemplate;

	@Override
	protected Result check() throws Exception {
		try {
			log.info("Requesting Service Health Check for {}", uri);
			restTemplate.headForHeaders(uri);
			log.info("Service Health Check available for {}", uri);
			return Result.healthy(uri + "available");
		} catch (Exception e) {
			log.error("Service Health Check failed", e);
			return Result.unhealthy(uri + " not available");
		}
	}
}