package ru.nfbgu.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pair<T, U> {
    T left;
    U right;
}
