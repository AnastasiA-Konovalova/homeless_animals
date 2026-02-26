package ru.animalservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.animalservice.model.VolunteerStatus;

@Getter
@Setter
public class VolunteerNewDto {

    private Long id;

    @NotBlank(message = "Email не должен быть пустым")
    private String email;

    @NotBlank
    @Size(min = 11, max = 12)
    private String phone;

    @NotBlank
    @Size(min = 2, max = 256)
    private String name;

    private VolunteerStatus volunteerStatus;
}
