package ru.nfbgu.controller;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import ru.nfbgu.controller.json.StudentInsertJson;
import ru.nfbgu.exceptions.CustomException;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.dto.StudentDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Inject
    StudentService studentService;

    @PostMapping("update")
    public ResponseEntity<?> update(String group, HttpServletRequest request) {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String id = (String) userId;
        try {
            StudentDto updated = studentService.update(id, group);
            return ResponseEntity.ok(updated);
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}
