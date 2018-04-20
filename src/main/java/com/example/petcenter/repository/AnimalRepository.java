package com.example.petcenter.repository;

import com.example.petcenter.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long>{
}
