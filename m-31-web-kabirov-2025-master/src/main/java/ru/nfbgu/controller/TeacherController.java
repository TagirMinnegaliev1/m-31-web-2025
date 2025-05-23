package ru.nfbgu.controller;

import jakarta.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nfbgu.controller.json.TeacherJson;
import ru.nfbgu.service.TeacherService;
import ru.nfbgu.service.dto.Pageable;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Inject
    TeacherService teacherService;

    @GetMapping("all")
    public Pageable<TeacherJson> getAllTeachers(@RequestParam int pageNumber) {
        Pageable<TeacherDto> allTeachers = teacherService.getAllTeachers(pageNumber);
        return allTeachers
                .map(t -> new TeacherJson(t.getId().toString(), t.getName()));
    }

    @GetMapping("all2")
    public Pageable<TeacherJson> getAllTeachers2(@RequestParam int pageNumber) {
        Page<TeacherDto> allTeachers2 = teacherService.getAllTeachers2(pageNumber);
        Page<TeacherJson> pageJson = allTeachers2
                .map(t -> new TeacherJson(t.getId().toString(), t.getName()));

        return new Pageable<>(
                pageJson.getContent(),
                pageJson.getNumber(),
                pageJson.getTotalPages(),
                (int) pageJson.getTotalElements()
        );
    }


    @GetMapping("all3")
    public Pageable<TeacherJson> getAllTeachers3(@RequestParam int pageNumber) {
        Pageable<TeacherDto> allTeachers = teacherService.getAllTeachers3(pageNumber);
        return allTeachers
                .map(t -> new TeacherJson(t.getId().toString(), t.getName()));
    }

    @GetMapping("first")
    public List<TeacherJson> getFirstTeachers(@RequestParam int count) {
        return teacherService.getFirstTeachers(count).stream()
                .map(t -> new TeacherJson(t.getId().toString(), t.getName()))
                .toList();
    }

}
