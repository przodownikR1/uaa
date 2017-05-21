package pl.java.scalatech.sample.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person extends AbstractEntity {

    private String name;
    private String login;
    private String city;
    private BigDecimal salary;
}
