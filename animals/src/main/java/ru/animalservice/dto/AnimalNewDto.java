package ru.animalservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalNewDto {

    private Long id;

    @NotNull(message = "Тип животного обязателен")
    @Min(value = 1, message = "ID типа животного должен быть положительным")
    private Long animalTypeId;

    @NotNull(message = "Количество животных обязательно")
    @Min(value = 1, message = "Количество должно быть положительным")
    private Long animalCount;

    @NotBlank(message = "Локация не должна быть пустой")
    @Size(max = 150, message = "Локация слишком длинная (максимум 150 символов)")
    private String location;
}
