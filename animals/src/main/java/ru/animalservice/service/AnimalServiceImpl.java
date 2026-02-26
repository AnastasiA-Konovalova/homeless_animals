package ru.animalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.animalservice.dto.AnimalDto;
import ru.animalservice.dto.AnimalNewDto;
import ru.animalservice.exception.NotFoundException;
import ru.animalservice.mapper.AnimalMapper;
import ru.animalservice.model.Animal;
import ru.animalservice.model.AnimalStatus;
import ru.animalservice.model.AnimalType;
import ru.animalservice.model.Volunteer;
import ru.animalservice.model.VolunteerStatus;
import ru.animalservice.repository.AnimalRepository;
import ru.animalservice.repository.AnimalTypeRepository;
import ru.animalservice.repository.VolunteerRepository;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final VolunteerRepository volunteerRepository;
    private final AnimalTypeRepository animalTypeRepository;

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
