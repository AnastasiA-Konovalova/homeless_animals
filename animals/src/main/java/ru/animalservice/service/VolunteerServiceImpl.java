package ru.animalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.animalservice.dto.VolunteerDto;
import ru.animalservice.dto.VolunteerNewDto;
import ru.animalservice.dto.VolunteerUpdateDto;
import ru.animalservice.exception.ConflictException;
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
                        new NotFoundException("Волонтер с id " + id + " не найден")
                );

        return volunteerMapper.toDto(volunteer);
    }

    @Override
    public VolunteerDto create(VolunteerNewDto newVolunteerDto) {
        uniqueEmailValidate(newVolunteerDto);
        Volunteer volunteer = VolunteerMapper.toEntity(newVolunteerDto);
        Volunteer savedVolunteer = volunteerRepository.save(volunteer);
        return VolunteerMapper.toDto(savedVolunteer);
    }

    @Override
    public VolunteerDto update(Long id, VolunteerUpdateDto updateVolunteerDto) {
        Volunteer volunteer = getVolunteerById(id);
        Volunteer updateVolunteer = VolunteerMapper.toUpdate(updateVolunteerDto, volunteer);

        return VolunteerMapper.toDto(updateVolunteer);
    }

    @Override
    public void delete(Long id) {
        getVolunteerById(id);
        volunteerRepository.deleteById(id);
    }

    private void uniqueEmailValidate(VolunteerNewDto newVolunteerDto) {
        Volunteer volunteer = volunteerRepository.findByEmail(newVolunteerDto.getEmail());
        if (volunteer != null) {
            throw new ConflictException("Волонтер с таким email " + volunteer.getEmail() + " уже существует");
        }
    }

    private Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(() -> new NotFoundException("Волонтера с таким id не найден"));
    }
}
