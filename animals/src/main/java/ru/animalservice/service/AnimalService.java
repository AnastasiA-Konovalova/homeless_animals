package ru.animalservice.service;

import ru.animalservice.dto.AnimalDto;
import ru.animalservice.dto.AnimalNewDto;

public interface AnimalService {

    AnimalDto add(AnimalNewDto animalNewDto);

    AnimalDto assignVolunteer(Long id, Long volunteerId);

    AnimalDto releaseFromVolunteer(Long id);

    void delete(Long id);
}
