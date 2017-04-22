package pl.java.scalatech.web;

import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.config.approach2.SecConfig;
import pl.java.scalatech.sample.domain.Car;

@RestController
@RequiredArgsConstructor
public class OAuthTestController {

	private final OAuth2RestOperations restTemplate;
	private final SecConfig secConfig;

	@GetMapping("/clientUser")
	String home() {
		return restTemplate.getForObject(secConfig.getBaseUrl() + "user", String.class);
	}

	@GetMapping("/clientCar")
	Car clientCar() {
		return restTemplate.getForObject(secConfig.getBaseUrl() + "car/1", Car.class);
	}

	@GetMapping("/clientoAuth")
	String hello(OAuth2Authentication authentication) {
		return "Hello " + authentication.getName();
	}

}
