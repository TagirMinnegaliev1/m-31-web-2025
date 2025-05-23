package ru.nfbgu.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nfbgu.controller.json.StudentInsertJson;
import ru.nfbgu.controller.json.StudentJsonResponse;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.dto.Pair;
import ru.nfbgu.service.dto.StudentDto;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StudentService studentService;

    @PostMapping("register")
    public void register(@RequestBody @Validated StudentInsertJson student, HttpServletRequest request) {
        String id = studentService.saveNewStudent(
                new StudentDto(
                        student.getName(),
                        student.getEmail(),
                        student.getGroup()
                ),
                student.getPassword()
        );
        HttpSession session = request.getSession();
        session.setAttribute("userId", id);

    }

    @PostMapping("login")
    public ResponseEntity<?> login(String email, String pass, HttpServletRequest request) {
        Pair<StudentDto, String> student = studentService.getStudentByEmailAndPass(email, pass);
        String id = student.getRight();
        request.getSession().setAttribute("userId", id);

        StudentJsonResponse response = new StudentJsonResponse(student.getLeft().getName(), student.getLeft().getGroup());

        return ResponseEntity.ok(response);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        request.getSession().setAttribute("userId", null);
    }
}
