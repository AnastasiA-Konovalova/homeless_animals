package ru.animalservice.service;

import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;

public interface VolunteerService {

    VolunteerDto get(Long id);

    VolunteerDto create(VolunteerNewDto newVolunteerDto);

    VolunteerDto update(Long id, VolunteerUpdateDto updateVolunteerDto);

    void delete(Long id);
}
