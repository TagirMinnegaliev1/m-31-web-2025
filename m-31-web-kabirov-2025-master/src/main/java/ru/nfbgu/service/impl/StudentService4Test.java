package ru.nfbgu.service.impl;

import org.springframework.stereotype.Service;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.dto.Pair;
import ru.nfbgu.service.dto.StudentDto;

import java.util.List;

public class StudentService4Test implements StudentService {

    public StudentService4Test() {
        System.out.println("Test implementation!!!!");
    }

    @Override
    public String saveNewStudent(StudentDto studentDto, String password) {
        return null;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return null;
    }

    @Override
    public Pair<StudentDto, String> getStudentByEmailAndPass(String email, String pass) {
        return null;
    }

    @Override
    public void changePass(StudentDto student, String oldPassword, String newPassword) {

    }

    @Override
    public StudentDto update(String id, String group) {
        return null;
    }
}
