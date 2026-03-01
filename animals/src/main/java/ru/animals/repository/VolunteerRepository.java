package ru.animals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.animals.model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByEmail(String email);


}
