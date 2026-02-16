package ru.animalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;
import ru.animalservice.service.VolunteerService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/volunteers")
public class VolunteersController {

    private final VolunteerService volunteerService;

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDto> getVolunteer(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.get(id));
    }

    @PostMapping
    public ResponseEntity<VolunteerDto> createVolunteer(
            @Valid @RequestBody VolunteerNewDto newVolunteerDto
    ) {
        VolunteerDto volunteer = volunteerService.create(newVolunteerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VolunteerDto> updateVolunteer(
            @PathVariable Long id,
            @Valid @RequestBody VolunteerUpdateDto updateVolunteerDto
    ) {
        return ResponseEntity.ok(
                volunteerService.update(id, updateVolunteerDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
