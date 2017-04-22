package pl.java.scalatech.sample.controller;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.Collection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.sample.domain.Car;

@RestController
@RequiredArgsConstructor
public class CarRestTemplateController {

	private final RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "defaultCars")
	@GetMapping(value = "/restTemplateCars")
	Collection<Car> getCars() {
		ResponseEntity<Collection<Car>> exchange = 
				this.restTemplate.exchange("http://car-service/car", GET, null,
				new ParameterizedTypeReference<Collection<Car>>() {
				});
		return exchange.getBody();

	}

	public ResponseEntity<?> defaultCars() {
		return new ResponseEntity<>(null, NO_CONTENT);
	}

}
