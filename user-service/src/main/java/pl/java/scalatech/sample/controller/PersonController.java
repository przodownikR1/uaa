package pl.java.scalatech.sample.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.sample.domain.Person;
import pl.java.scalatech.sample.repo.PersonRepo;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('read')")
    Person getPersonById(@PathVariable Long id) {
        return personRepo.findOne(id);
    }

    @GetMapping("/")
    @PreAuthorize("#oauth2.hasScope('read')")
    List<Person> getAll() {
        return personRepo.findAll();
    }

}
