package pl.java.scalatech.sample.generator;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import pl.java.scalatech.sample.domain.Car;

@Component
class CarGenerator {
    private Random r = new Random();
    private String cars[] = new String[] { "Dacia", "AlfaRomeo", "Nissan", "Toyota", "Opel", "Ferrari", "Porche", "Fiat", "BMW", "Mazda", "Ford", "Subaru",
            "Skoda", "VW", "Jaguar", "Jeep", "Mercedes", "Renault" };

    private Faker faker;

    public CarGenerator() {
        faker = new Faker();
    }

    // @formatter:off    
    public Car generateSingleCar() {
        
         BigDecimal carValue = BigDecimal.valueOf(faker.number().numberBetween(2000, 11000));
        
        Car car = Car.builder()                                              
                .color(faker.color().name())
                .name(cars[r.nextInt(cars.length-1)])               
                .age(faker.number().numberBetween(1, 25))
                .value(carValue)                
               
                .build();                
        return car;
    }
 // @formatter:on
}
