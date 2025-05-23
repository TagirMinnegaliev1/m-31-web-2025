package ru.nfbgu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import ru.nfbgu.service.Json;

public class JsonImpl implements Json {
    @Inject ObjectMapper om;

    @Override
    @SneakyThrows
    public <T> String to(T value) {
        return om.writeValueAsString(value);
    }

    @Override
    @SneakyThrows
    public <T> T from(String s, Class<T> cls) {
        return om.readValue(s, cls);
    }


}
