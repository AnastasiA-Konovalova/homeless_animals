package ru.animals.dto;

import lombok.Getter;
import lombok.Setter;
import ru.animals.model.AnimalStatus;
import ru.animals.model.Volunteer;

@Setter
@Getter
public class AnimalDto {

    private Long id;

    private String type;

    private String address;

    private AnimalStatus animalStatus;

    private Volunteer volunteer;
}
