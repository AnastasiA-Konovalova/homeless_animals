package ru.animalservice.mapper;

import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.model.Volunteer;

public class VolunteerMapper {

    public static VolunteerDto toDto(Volunteer volunteer) {
        VolunteerDto volunteerDto = new VolunteerDto();
        volunteerDto.setName(volunteer.getName());
        volunteerDto.setPhone(volunteer.getPhone());
        volunteerDto.setStatus(volunteer.getStatus());

        return volunteerDto;
    }
}
