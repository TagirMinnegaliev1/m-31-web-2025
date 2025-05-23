package ru.nfbgu.service;

import ru.nfbgu.service.dto.Pair;
import ru.nfbgu.service.dto.StudentDto;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    String saveNewStudent(StudentDto studentDto, String password);

    List<StudentDto> getAllStudents();

    Pair<StudentDto, String> getStudentByEmailAndPass(String email, String pass);

    void changePass(StudentDto student, String oldPassword, String newPassword);

    StudentDto update(String id, String group);
}
