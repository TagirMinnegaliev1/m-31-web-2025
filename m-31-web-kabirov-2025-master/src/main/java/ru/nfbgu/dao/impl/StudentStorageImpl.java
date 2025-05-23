package ru.nfbgu.dao.impl;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nfbgu.dao.StudentStorage;
import ru.nfbgu.dao.model.StudentModel;
import ru.nfbgu.dao.repo.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentStorageImpl implements StudentStorage {
    @Inject
    StudentRepository studentRepo;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<StudentModel> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    @Transactional
    public StudentModel insertStudent(StudentModel student) {
       return studentRepo.save(student);
    }

    @Override
    @Transactional
    public void updateStudent(StudentModel student) {
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentRepo.deleteById(UUID.fromString(studentId));
    }

    @Override
    public Optional<StudentModel> getStudentById(String studentId) {
       return studentRepo.findById(UUID.fromString(studentId));
    }

    @Override
    public Optional<StudentModel> getStudentByEmail(String email) {
       return studentRepo.findFirstByEmail(email);
    }
}
