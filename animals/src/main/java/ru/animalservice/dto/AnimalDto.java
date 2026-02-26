package ru.animalservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import ru.animalservice.model.AnimalStatus;
import ru.animalservice.model.Volunteer;

@Setter
@Getter
public class AnimalDto {

    private Long id;

    private String type;

    private String address;

    private AnimalStatus animalStatus;

    private Volunteer volunteer;
}
