package ru.nfbgu.service;

import ru.nfbgu.service.dto.DisciplineDto;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.List;

public interface DisciplineService {
    void insert(DisciplineDto discipline, TeacherDto teacher);

    List<DisciplineDto> getAllDisciplines();

    List<DisciplineDto> getDisciplinesByTeacherId(String teacherId);
}
