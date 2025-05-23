package ru.nfbgu.service;

import org.springframework.data.domain.Page;
import ru.nfbgu.service.dto.Pageable;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    void insertTeacher(TeacherDto teacher);

    List<TeacherDto> getAllTeachers();

    Pageable<TeacherDto> getAllTeachers(int pageNumber);

    Page<TeacherDto> getAllTeachers2(int pageNumber);

    Pageable<TeacherDto> getAllTeachers3(int pageNumber);

    List<TeacherDto> getFirstTeachers(int count);

    Optional<TeacherDto> getTeacherById(String teacherId);
}
