package pl.java.scalatech.sample.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.java.scalatech.sample.domain.Person;

@RepositoryRestResource(collectionResourceRel = "person", path = "v1")
interface UserRestRepository extends PagingAndSortingRepository<Person, Long> {

}
