package com.github.aetherwisp.volvox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Volvox {
    public static class Profiles {
        public static final String DEVELOP = "develop";
        public static final String STAGING = "staging";
        public static final String PRODUCT = "product";
    }

    public static void main(String[] _args) {
        System.err.println(System.getProperty("java.io.tmpdir"));
        SpringApplication.run(Volvox.class, _args);
    }

}
