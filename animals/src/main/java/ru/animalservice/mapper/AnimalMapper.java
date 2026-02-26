package ru.animalservice.mapper;

import org.springframework.stereotype.Component;
import ru.animalservice.dto.AnimalDto;
import ru.animalservice.dto.AnimalNewDto;
import ru.animalservice.model.Animal;
import ru.animalservice.model.AnimalStatus;

@Component
public class AnimalMapper {

    public static Animal toEntity(AnimalNewDto dto, String typeName) {
        Animal animal = new Animal();
        animal.setAddress(dto.getLocation());
        animal.setType(typeName);
        animal.setAnimalStatus(AnimalStatus.NOT_UNDER_PATRONAGE);
        animal.setAnimalCount(dto.getAnimalCount());
        return animal;
    }

    public static AnimalDto toDto(Animal animal) {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setAnimalStatus(animal.getAnimalStatus());
        animalDto.setAddress(animal.getAddress());
        animalDto.setType(animal.getType());

        return animalDto;
    }

}
