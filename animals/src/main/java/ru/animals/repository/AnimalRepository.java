package ru.animals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animals.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
