package pl.java.scalatech.sample.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.java.scalatech.sample.domain.Car;
import pl.java.scalatech.sample.repo.CarRepo;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

	private final CarRepo carRepo;

	@GetMapping("/{id}")
	@PreAuthorize("#oauth2.hasScope('read')")
	Car getById(@PathVariable Long id) {
		return carRepo.findOne(id);
	}

	@GetMapping("/name/{name}")
	@PreAuthorize("#oauth2.hasScope('read')")
	Iterable<Car> findByName(@PathVariable String name) {
		return carRepo.findByNameLike(name);
	}

	@GetMapping("/color/{color}")
	Iterable<Car> findByColor(@PathVariable String color) {
		return carRepo.findByColorLike(color);
	}

	@GetMapping("/")
	@PreAuthorize("#oauth2.hasScope('read')")
	Iterable<Car> getAll() {
		return carRepo.findAll();
	}
}
