package ru.nfbgu;

import jakarta.inject.Inject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.nfbgu.config.MainConfig;
import ru.nfbgu.ui.ConsoleUi;

@SpringBootApplication
public class Main {
    @Inject
    ConsoleUi consoleUi;

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{Main.class, MainConfig.class}, args);
    }

}
