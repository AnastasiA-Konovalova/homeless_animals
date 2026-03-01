package ru.animals.service;

import org.springframework.data.domain.Page;
import ru.animals.dto.AnimalDto;
import ru.animals.dto.AnimalNewDto;

public interface AnimalService {

    Page<AnimalDto> get(int page, int size, String sortBy, String sortDir);

    AnimalDto getById(Long id);

    AnimalDto add(AnimalNewDto animalNewDto);

    AnimalDto assignVolunteer(Long id, Long volunteerId);

    AnimalDto releaseFromVolunteer(Long id);

    void delete(Long id);
}
