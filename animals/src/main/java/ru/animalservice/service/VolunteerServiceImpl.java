package ru.animalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;
import ru.animalservice.exception.NotFoundException;
import ru.animalservice.mapper.VolunteerMapper;
import ru.animalservice.model.Volunteer;
import ru.animalservice.repository.VolunteerRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerMapper volunteerMapper;

    @Override
    public VolunteerDto get(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Volunteer with id=" + id + " not found")
                );

        return volunteerMapper.toDto(volunteer);
    }

    @Override
    public ResponseEntity<VolunteerDto> createVolunteer(VolunteerNewDto newVolunteerDto) {
        return null;
    }

    @Override
    public ResponseEntity<VolunteerDto> updateVolunteer(Long id, VolunteerUpdateDto updateVolunteerDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteVolunteer(Long id) {
        return null;
    }


}
