package ru.nfbgu.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({
      //  ConnectionConfiguration.class,
        ServiceConfig.class
})
public class MainConfig {
    @Value("${custom.property}")
    String property;

    @Value("${custom.default}")
    String def;

    @PostConstruct
    void init() {
        System.out.println(property);
        System.out.println(def);
    }
}
