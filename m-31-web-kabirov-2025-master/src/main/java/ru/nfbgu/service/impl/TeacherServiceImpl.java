package ru.nfbgu.service.impl;

import org.springframework.data.domain.Page;
import ru.nfbgu.dao.TeacherStorage;
import ru.nfbgu.dao.model.TeacherModel;
import org.springframework.stereotype.Service;
import ru.nfbgu.service.TeacherService;
import ru.nfbgu.service.dto.Pageable;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherStorage teacherStorage;

    public TeacherServiceImpl(TeacherStorage teacherStorage) {
        this.teacherStorage = teacherStorage;
    }

    @Override
    public void insertTeacher(TeacherDto teacher) {
        teacherStorage.insert(new TeacherModel(
                UUID.randomUUID(),
                teacher.getName(),
                Collections.emptyList()
        ));
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherStorage.getAllTeachers().stream()
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Pageable<TeacherDto> getAllTeachers(int pageNumber) {
        Pageable<TeacherModel> teachers = teacherStorage.getAllTeachersByPage(pageNumber);
        return teachers
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ));
    }

    @Override
    public Page<TeacherDto> getAllTeachers2(int pageNumber) {
        Page<TeacherModel> teachers = teacherStorage.getAllTeachersByPage2(pageNumber);
        return teachers
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ));
    }

    @Override
    public Pageable<TeacherDto> getAllTeachers3(int pageNumber) {
        Pageable<TeacherModel> teachers = teacherStorage.getAllTeachersByPage3(pageNumber);
        return teachers
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ));
    }

    @Override
    public List<TeacherDto> getFirstTeachers(int count) {
       return teacherStorage.getFirstTeachers(count).stream()
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ))
                .toList();
    }

    @Override
    public Optional<TeacherDto> getTeacherById(String teacherId) {
        return teacherStorage.getTeacherById(UUID.fromString(teacherId))
                .map(t -> new TeacherDto(
                        t.getId(),
                        t.getName()
                ));
    }
}
