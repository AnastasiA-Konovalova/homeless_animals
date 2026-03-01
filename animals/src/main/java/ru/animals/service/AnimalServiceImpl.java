package ru.animals.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.animals.dto.AnimalDto;
import ru.animals.dto.AnimalNewDto;
import ru.animals.exception.NotFoundException;
import ru.animals.mapper.AnimalMapper;
import ru.animals.model.Animal;
import ru.animals.model.AnimalStatus;
import ru.animals.model.AnimalType;
import ru.animals.model.Volunteer;
import ru.animals.model.VolunteerStatus;
import ru.animals.repository.AnimalRepository;
import ru.animals.repository.AnimalTypeRepository;
import ru.animals.repository.VolunteerRepository;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final VolunteerRepository volunteerRepository;
    private final AnimalTypeRepository animalTypeRepository;

    @Override
    public Page<AnimalDto> get(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Animal> animalPage = animalRepository.findAll(pageable);

        return animalPage.map(AnimalMapper::toDto);
    }

    @Override
    public AnimalDto getById(Long id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new NotFoundException("Животное с таким id " +
                id + " не найдено"));
        return AnimalMapper.toDto(animal);
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalDto add(AnimalNewDto dto) {
        AnimalType animalType = animalTypeRepository
                .findById(dto.getAnimalTypeId())
                .orElseThrow(() -> new NotFoundException("Тип животного не найден: " + dto.getAnimalTypeId()));

        Animal animal = AnimalMapper.toEntity(dto, animalType.getType());
        Animal saved = animalRepository.save(animal);

        return AnimalMapper.toDto(saved);
    }

    @Override
    public AnimalDto assignVolunteer(Long id, Long volunteerId) {
        Animal animal = getAnimalById(id);
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new NotFoundException("Волонтёр с таким id " + volunteerId + " не найден"));

        if (animal.getVolunteer() != null) {
            throw new IllegalStateException("У животного уже назначен волонтёр");
        }
        if (VolunteerStatus.ACTIVE != volunteer.getVolunteerStatus()) {
            throw new IllegalStateException("Волонтёр не активен");
        }
        volunteer.addAnimal(animal);
        animal.setVolunteer(volunteer);

        animal.setAnimalStatus(AnimalStatus.UNDER_PATRONAGE);
        Animal updatedAnimal = animalRepository.save(animal);
        return AnimalMapper.toDto(updatedAnimal);
    }

    @Override
    public AnimalDto releaseFromVolunteer(Long id) {
        Animal animal = getAnimalById(id);
        Volunteer currentVolunteer = animal.getVolunteer();
        if (currentVolunteer == null) {
            return AnimalMapper.toDto(animal);
        }

        currentVolunteer.removeAnimal(animal);
        animal.setAnimalStatus(AnimalStatus.NOT_UNDER_PATRONAGE);

        Animal updatedAnimal = animalRepository.save(animal);

        return AnimalMapper.toDto(updatedAnimal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = getAnimalById(id);
        if (animal.getVolunteer() != null) {
            animal.getVolunteer().removeAnimal(animal);
        }
        animalRepository.delete(animal);
    }

    private Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException("Животное с таким id " +
                id + " не найдено"));
    }
}
