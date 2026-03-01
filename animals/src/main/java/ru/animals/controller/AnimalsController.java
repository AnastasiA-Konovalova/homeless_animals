package ru.animals.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.animals.dto.AnimalDto;
import ru.animals.dto.AnimalNewDto;
import ru.animals.dto.AssignVolunteerDto;
import ru.animals.service.AnimalService;

@Tag(name = "Animals", description = "Операции с животными")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/animals")
public class AnimalsController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<Page<AnimalDto>> getAnimals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<AnimalDto> animals = animalService.get(page, size, sortBy, sortDir);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimal(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getById(id));
    }

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
