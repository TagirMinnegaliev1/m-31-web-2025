package ru.nfbgu.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.nfbgu.dao.model.StudentModel;

import java.util.List;
import java.util.Optional;

public interface StudentStorage {
    List<StudentModel> getAllStudents();

    StudentModel insertStudent(StudentModel student);

    void updateStudent(StudentModel student);

    void deleteStudent(String studentId);

    Optional<StudentModel> getStudentById(String studentId);

    Optional<StudentModel> getStudentByEmail(String email);
}
