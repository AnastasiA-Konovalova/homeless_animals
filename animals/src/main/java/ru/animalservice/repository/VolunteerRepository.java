package ru.animalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animalservice.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
