package ru.animalservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignVolunteerDto {

    @NotNull(message = "ID волонтёра обязателен")
    @Min(value = 1, message = "ID волонтёра должен быть положительным числом")
    private Long volunteerId;


}
