package ru.nfbgu.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nfbgu.exceptions.CustomException;
import ru.nfbgu.service.DisciplineService;
import ru.nfbgu.service.Json;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.TeacherService;
import ru.nfbgu.service.dto.DisciplineDto;
import ru.nfbgu.service.dto.StudentDto;
import ru.nfbgu.service.dto.TeacherDto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class ConsoleUi {
    private final StudentService studentService;
    private final StudentLk studentLk;
    private final TeacherService teacherService;
    private final DisciplineService disciplineService;
    private final Json json;

    public void start() {

        String command = "";
        while (!command.equals("exit")) {
            System.out.println("""
                    You are in main console UI
                    1 - insert student\t\t2 - print all students\t3 - sign in as student
                    4 - insert teacher\t\t5 - print all teachers\t6 - print teachers disciplines
                    7 - print teachers with disciplines
                    8 - insert discipline\t9 - print all disciplines
                                        
                    exit - exit
                    """);
            Scanner scanner = new Scanner(System.in);
            command = scanner.next();
            switch (command) {
                case "1" -> insertStudent();
                case "2" -> printAllStudents();
                case "3" -> signIn();
                case "4" -> insertTeacher();
                case "5" -> printAllTeachers();
                case "6" -> printTeachersDisciplines();
                case "7" -> printTeachersWithDisciplines();
                case "8" -> insertDiscipline();
                case "9" -> printAllDisciplines();

            }
        }

    }

    private void printTeachersWithDisciplines() {

    }

    private void printTeachersDisciplines() {
        String teacherId = getString(Optional.of("enter teacher id"));
        Optional<TeacherDto> teacher = teacherService.getTeacherById(teacherId);

        if (teacher.isEmpty()) {
            System.out.println("Teacher not found by id");
            return;
        }

        List<DisciplineDto> disciplineList = disciplineService.getDisciplinesByTeacherId(teacherId);
        disciplineList.forEach(System.out::println);
    }

    private void printAllDisciplines() {
        disciplineService.getAllDisciplines()
                .forEach(System.out::println);
    }

    private void printAllTeachers() {
        teacherService.getAllTeachers()
                .forEach(System.out::println);
    }

    private void insertDiscipline() {
        String teacherId = getString(Optional.of("enter teacher id"));
        Optional<TeacherDto> teacher = teacherService.getTeacherById(teacherId);

        if (teacher.isEmpty()) {
            System.out.println("Teacher not found by id");
            return;
        }

        String name = getString(Optional.of("enter discipline name"));

        disciplineService.insert(
                new DisciplineDto(null, name),
                teacher.get()
        );
    }

    private void insertTeacher() {
        String name = getString(Optional.of("enter teacher name"));
        teacherService.insertTeacher(
                new TeacherDto(
                        null,
                        name
                )
        );
    }

    private void signIn() {
        String email = getString(Optional.of("enter email"));
        String pass = getString(Optional.of("enter pass"));

        StudentDto student;
        try {
            student =
                    studentService.getStudentByEmailAndPass(email, pass).getLeft();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return;
        }

        studentLk.start(student);
    }


    private void insertStudent() {
        String name = getString(Optional.of("enter name"));
        String email = getString(Optional.of("enter email"));
        String pass = getString(Optional.of("enter pass"));
        String group = getString(Optional.of("enter group"));

        studentService.saveNewStudent(
                new StudentDto(
                        name,
                        email,
                        group
                ),
                pass
        );

    }


    private void printAllStudents() {
        studentService.getAllStudents().stream()
                .map(json::to)
                .forEach(System.out::println);
    }

    private String getString(Optional<String> text) {
        text.ifPresent(System.out::println);

        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
