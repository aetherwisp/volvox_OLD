package com.github.aetherwisp.volvox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VolvoxApplication {
    static {
        System.setProperty("java.awt.headless", "false");
    }

    public static void main(String[] _args) {
        SpringApplication.run(VolvoxApplication.class, _args);
    }

}
