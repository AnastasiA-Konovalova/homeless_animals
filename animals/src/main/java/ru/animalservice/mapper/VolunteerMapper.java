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
        volunteerDto.setVolunteerStatus(volunteer.getVolunteerStatus());

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
        if (volunteerUpdateDto.getVolunteerStatus() != null) volunteer.setVolunteerStatus(volunteerUpdateDto.getVolunteerStatus());
        if (volunteerUpdateDto.getPhone() != null) volunteer.setPhone(volunteerUpdateDto.getPhone());
        if (volunteerUpdateDto.getName() != null) volunteer.setName(volunteerUpdateDto.getName());
        return volunteer;
    }
}

