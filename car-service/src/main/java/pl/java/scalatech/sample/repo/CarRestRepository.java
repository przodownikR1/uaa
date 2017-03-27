package pl.java.scalatech.sample.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.java.scalatech.sample.domain.Car;

@RepositoryRestResource(collectionResourceRel = "car", path = "v1")
interface CarRestRepository extends PagingAndSortingRepository<Car, Long> {}

