package ru.nfbgu.controller.json;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentInsertJson {
    @NotBlank
    String name;
    @Email
    String email;

    @NotBlank
    String group;

    @NotBlank
    @Size(min = 5)
    String password;
}
