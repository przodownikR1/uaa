package pl.java.scalatech.sample.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.ToString;

@ToString
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    @Getter
    protected Long id;

    @Version
    protected Long version;
}
