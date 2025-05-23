package ru.nfbgu.service.impl;

import ru.nfbgu.dao.DisciplineStorage;
import ru.nfbgu.dao.model.DisciplineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nfbgu.service.DisciplineService;
import ru.nfbgu.service.dto.DisciplineDto;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineStorage disciplineStorage;

    @Override
    public void insert(DisciplineDto discipline, TeacherDto teacher) {
        disciplineStorage.insert(
                new DisciplineModel(
                        UUID.randomUUID(),
                        discipline.getName(),
                        teacher.getId(),
                        null,
                        null
                )
        );
    }

    @Override
    public List<DisciplineDto> getAllDisciplines() {
        return  disciplineStorage.getAllDisciplines().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }



    @Override
    public List<DisciplineDto> getDisciplinesByTeacherId(String teacherId) {
        return disciplineStorage.getDisciplinesByTeacherId(UUID.fromString(teacherId)).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }


    private DisciplineDto convert(DisciplineModel d) {
        return new DisciplineDto(
                d.getId(),
                d.getName()
        );
    }
}
