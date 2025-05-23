package ru.nfbgu.ui;

import ru.nfbgu.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.dto.StudentDto;

import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class StudentLk {
    private final StudentService studentService;

    public void start(StudentDto student) {
        String command = "";
        while (!command.equals("exit")) {
            System.out.printf("""
                    Welcome, %s!
                    1 - show my info
                    2 - change password
                    exit - exit
                    %n""", student.getName());
            Scanner scanner = new Scanner(System.in);
            command = scanner.next();
            switch (command) {
                case "1" -> showInfo(student);
                case "2" -> changePassword(student);
            }
        }
    }


    private void showInfo(StudentDto student) {
        System.out.println(student);
    }

    private void changePassword(StudentDto student) {
        String oldPassword = getString(Optional.of("Enter old password"));
        String newPassword = getString(Optional.of("Enter new password"));
        String newPassword2 = getString(Optional.of("Repeat new password"));

        if (!newPassword.equals(newPassword2)) {
            System.out.println("new passwords are not same");
            return;
        }

        try {
            studentService.changePass(student, oldPassword, newPassword);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Pass was changed");

    }

    private String getString(Optional<String> text) {
        text.ifPresent(System.out::println);

        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
