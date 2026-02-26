package ru.animalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.animalservice.dto.AnimalDto;
import ru.animalservice.dto.AnimalNewDto;
import ru.animalservice.dto.AssignVolunteerDto;
import ru.animalservice.model.Animal;
import ru.animalservice.service.AnimalService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/animals")
public class AnimalsController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDto> addAnimal(
            @Valid @RequestBody AnimalNewDto animalNewDto
            ) {
        AnimalDto animal = animalService.add(animalNewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<AnimalDto> assignToVolunteer(
            @PathVariable Long id,
            @RequestBody @Valid AssignVolunteerDto dto) {

        AnimalDto updated = animalService.assignVolunteer(id, dto.getVolunteerId());
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/volunteer")
    public ResponseEntity<AnimalDto> releaseVolunteer(@PathVariable Long id) {
        AnimalDto updated = animalService.releaseFromVolunteer(id);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

}
