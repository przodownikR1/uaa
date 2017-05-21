package pl.java.scalatech.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.sample.domain.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {

}
