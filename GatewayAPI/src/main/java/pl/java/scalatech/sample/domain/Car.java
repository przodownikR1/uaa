package pl.java.scalatech.sample.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends AbstractEntity{

    private String name;
    private String color;
    private int age;
    @Column(name="carValue")
    private BigDecimal value;
    @ManyToOne
    private Person owner;
    
}
