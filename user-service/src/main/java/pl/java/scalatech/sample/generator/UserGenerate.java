package pl.java.scalatech.sample.generator;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import pl.java.scalatech.sample.domain.Person;

@Component
class UserGenerate {

    private Random r = new Random();
    private Faker faker = new Faker();

    // @formatter:off    
    public Person generateSingleUser() { 
        Person user = Person.builder()               
                .city(faker.address().city())                
                .login(faker.name().fullName())
                .name(faker.name().lastName())               
                .salary(new BigDecimal(r.nextInt(10000)))
                .build();              
        return user;
    }
 // @formatter:on

}
