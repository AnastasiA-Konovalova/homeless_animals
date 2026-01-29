package ru.animalservice.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;

public interface VolunteerService {

    VolunteerDto get(Long id);

    ResponseEntity<VolunteerDto> createVolunteer(VolunteerNewDto newVolunteerDto);

    ResponseEntity<VolunteerDto> updateVolunteer(Long id, VolunteerUpdateDto updateVolunteerDto);

    ResponseEntity<Void> deleteVolunteer(Long id);
}
