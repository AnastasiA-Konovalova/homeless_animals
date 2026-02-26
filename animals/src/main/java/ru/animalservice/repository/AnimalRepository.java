package ru.animalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animalservice.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
