package pl.java.scalatech.sample.generator;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import pl.java.scalatech.sample.domain.Car;
import pl.java.scalatech.sample.domain.Person;

@Component
class CarGenerator {
	private Random r = new Random();
	private String cars[] = new String[] { "Dacia", "AlfaRomeo", "Nissan", "Toyota", "Opel", "Ferrari", "Porche",
			"Fiat", "BMW", "Mazda", "Ford", "Subaru", "Skoda", "VW", "Jaguar", "Jeep", "Mercedes", "Renault" };

	private Faker faker;

	public CarGenerator() {
		faker = new Faker();
	}

	// @formatter:off
	public Car generateSingleCar(Person person) {
		BigDecimal carValue;
		if (person.getSalary().doubleValue() < 2000) {
			carValue = BigDecimal.valueOf(faker.number().numberBetween(2000, 11000));
		} else if (person.getSalary().doubleValue() > 2000 && person.getSalary().doubleValue() < 5000) {
			carValue = BigDecimal.valueOf(faker.number().numberBetween(5000, 41000));
		} else {
			carValue = BigDecimal.valueOf(faker.number().numberBetween(41000, 150000));
		}

		Car car = Car.builder().color(faker.color().name()).name(cars[r.nextInt(cars.length - 1)])
				.age(faker.number().numberBetween(1, 25)).value(carValue).owner(person).build();
		return car;
	}
	// @formatter:on
}
