package ru.nfbgu.service;

public interface Json {
    <T> String to(T value);
    <T> T from(String s, Class<T> cls);
}
