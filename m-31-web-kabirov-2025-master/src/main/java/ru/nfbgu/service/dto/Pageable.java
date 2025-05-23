package ru.nfbgu.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Pageable<T> {
    List<T> data;
    int currentPage;
    int totalPages;
    int totalItems;

    public <R> Pageable<R> map(Function<T, R> mapper) {
        List<R> data = this.data.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new Pageable<>(
                data,
                this.currentPage,
                this.totalPages,
                this.totalItems);
    }
}
