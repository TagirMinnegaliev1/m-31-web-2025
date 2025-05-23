package ru.nfbgu.controller.errorhandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FieldErrorJson {
    final String field;
    final String error;

    String descr = "u dumbass";
}
