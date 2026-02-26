package ru.animalservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.animalservice.model.VolunteerStatus;

@Setter
@Getter
public class VolunteerDto {

    private Long id;

    private String phone;

    private String email;

    private String name;

    private VolunteerStatus volunteerStatus;
}
