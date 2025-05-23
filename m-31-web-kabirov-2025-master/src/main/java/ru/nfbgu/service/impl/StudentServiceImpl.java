package ru.nfbgu.service.impl;

import jakarta.inject.Inject;
import ru.nfbgu.converters.Converter;
import ru.nfbgu.dao.StudentStorage;
import ru.nfbgu.dao.model.StudentModel;
import ru.nfbgu.exceptions.CustomException;
import ru.nfbgu.service.PasswordHashService;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.dto.Pair;
import ru.nfbgu.service.dto.StudentDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    @Inject
    private StudentStorage studentStorage;
    @Inject
    private Converter<StudentDto, StudentModel> converter;
    @Inject
    private PasswordHashService hashService;

    @Override
    public String saveNewStudent(StudentDto studentDto, String password) {
        StudentModel model = converter.to(studentDto);
        UUID id = UUID.randomUUID();
        model.setId(id);
        model.setPassHash(hashService.getHash(password));

        studentStorage.insertStudent(model);
        return id.toString();
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentModel> allStudents = studentStorage.getAllStudents();
        return allStudents.stream()
                .map(converter::from)
                .collect(Collectors.toList());
    }

    @Override
    public Pair<StudentDto, String> getStudentByEmailAndPass(String email, String pass) {
        String errMsg = "Email and pass mismatch";
        return getStudentByEmailAndPassPrivate(email, pass, errMsg, errMsg);
    }

    @Override
    public void changePass(StudentDto student, String oldPassword, String newPassword) {
        getStudentByEmailAndPassPrivate(
                student.getEmail(),
                oldPassword,
                "Old pass not matched",
                "Not found student by email");

        StudentModel model = converter.to(student);
        String hash = hashService.getHash(newPassword);
        model.setPassHash(hash);

        studentStorage.updateStudent(model);
    }

    @Override
    public StudentDto update(String id, String group) {
        StudentModel student = studentStorage.getStudentById(id)
                .orElseThrow(() -> new CustomException("Student not found by id"));

        student.setGroup(group);
        studentStorage.updateStudent(student);

        return converter.from(student);
    }

    private Pair<StudentDto, String> getStudentByEmailAndPassPrivate(
            String email,
            String pass,
            String passMismatchErrMsg,
            String notFoundErrMsg
    ) {
        Optional<StudentModel> studentModel =
                studentStorage.getStudentByEmail(email);

        return studentModel
                .map(
                        student -> {
                            String passHash = student.getPassHash();
                            boolean passwordMatches =
                                    hashService.checkPass(passHash, pass);
                            if (!passwordMatches) {
                                throw new CustomException(passMismatchErrMsg);
                            }
                            return student;
                        }
                )
                .map(s -> new Pair<StudentDto, String>(converter.from(s), s.getId().toString()))
                .orElseThrow(() -> new CustomException(notFoundErrMsg));
    }
}
