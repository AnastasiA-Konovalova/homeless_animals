package ru.animalservice.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.animalservice.model.Status;

@Getter
@Setter
public class VolunteerUpdateDto {

    private String email;

    @Size(min = 11, max = 12)
    private String phone;

    @Size(min = 2, max = 256)
    private String name;

    private Status status;
}
