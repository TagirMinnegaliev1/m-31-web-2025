package ru.nfbgu.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nfbgu.dao.StudentStorage;
import ru.nfbgu.dao.impl.StudentStorageImpl;
import ru.nfbgu.service.Json;
import ru.nfbgu.service.StudentService;
import ru.nfbgu.service.impl.JsonImpl;
import ru.nfbgu.service.impl.StudentService4Test;
import ru.nfbgu.service.impl.StudentServiceImpl;

@Configuration
public class ServiceConfig {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    StudentService studentService() {
        if (activeProfile.equals("local")) {
            return new StudentService4Test();
        }
        return new StudentServiceImpl();
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        return om;
    }

    @Bean
    Json json() {
        return new JsonImpl();
    }

    @Bean
    StudentStorage studentStorage() {
        return new StudentStorageImpl();
    }
}
