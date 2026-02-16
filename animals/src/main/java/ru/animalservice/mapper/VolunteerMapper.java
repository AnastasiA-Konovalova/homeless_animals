package ru.animalservice.mapper;

import org.springframework.stereotype.Component;
import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;
import ru.animalservice.model.Volunteer;

@Component
public class VolunteerMapper {

    public static VolunteerDto toDto(Volunteer volunteer) {
        VolunteerDto volunteerDto = new VolunteerDto();
        volunteerDto.setName(volunteer.getName());
        volunteerDto.setPhone(volunteer.getPhone());
        volunteerDto.setStatus(volunteer.getStatus());

        return volunteerDto;
    }

    public static Volunteer toEntity(VolunteerNewDto volunteerDto) {
        Volunteer volunteer = new Volunteer();

        volunteer.setId(volunteerDto.getId());
        volunteer.setEmail(volunteerDto.getEmail());
        volunteer.setName(volunteerDto.getName());
        return volunteer;
    }

    public static Volunteer toUpdate(VolunteerUpdateDto volunteerUpdateDto, Volunteer volunteer) {
        if (volunteerUpdateDto.getEmail() != null) volunteer.setEmail(volunteerUpdateDto.getEmail());
        if (volunteerUpdateDto.getStatus() != null) volunteer.setStatus(volunteerUpdateDto.getStatus());
        if (volunteerUpdateDto.getPhone() != null) volunteer.setPhone(volunteerUpdateDto.getPhone());
        if (volunteerUpdateDto.getName() != null) volunteer.setName(volunteerUpdateDto.getName());
        return volunteer;
    }
}

