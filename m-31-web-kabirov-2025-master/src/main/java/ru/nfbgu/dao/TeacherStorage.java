package ru.nfbgu.dao;

import org.springframework.data.domain.Page;
import ru.nfbgu.dao.model.TeacherModel;
import ru.nfbgu.service.dto.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherStorage {
    void insert(TeacherModel teacher);

    List<TeacherModel> getAllTeachers();

    Pageable<TeacherModel> getAllTeachersByPage(int pageNumber);

    Page<TeacherModel> getAllTeachersByPage2(int pageNumber);

    Pageable<TeacherModel> getAllTeachersByPage3(int pageNumber);

    List<TeacherModel> getFirstTeachers(int count);

    Optional<TeacherModel> getTeacherById(UUID id);
}
