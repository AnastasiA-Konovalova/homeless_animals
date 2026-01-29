package ru.animalservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import ru.animalservice.model.Status;

import java.time.LocalDateTime;

@Setter
@Getter
public class VolunteerDto {

    private String phone;

    private String name;

    private Status status;
}
