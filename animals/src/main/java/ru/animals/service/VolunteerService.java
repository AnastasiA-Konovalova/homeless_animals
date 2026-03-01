package ru.animals.service;

import ru.animals.dto.VolunteerDto;
import ru.animals.dto.VolunteerNewDto;
import ru.animals.dto.VolunteerUpdateDto;

public interface VolunteerService {

    VolunteerDto get(Long id);

    VolunteerDto create(VolunteerNewDto newVolunteerDto);

    VolunteerDto update(Long id, VolunteerUpdateDto updateVolunteerDto);

    void delete(Long id);
}
