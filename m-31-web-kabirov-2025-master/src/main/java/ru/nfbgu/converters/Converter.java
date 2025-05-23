package ru.nfbgu.converters;

public interface Converter<T, R> {
    R to(T source);

    T from(R source);
}
