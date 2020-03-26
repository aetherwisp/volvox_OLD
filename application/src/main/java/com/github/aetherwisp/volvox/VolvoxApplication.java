package com.github.aetherwisp.volvox;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class VolvoxApplication {

    public static void main(String[] _args) {
        //        SpringApplication.run(VolvoxApplication.class, _args);

        Application.launch(JavaFxApplication.class, _args);
    }

}
