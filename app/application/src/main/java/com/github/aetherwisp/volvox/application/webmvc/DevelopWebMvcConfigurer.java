package com.github.aetherwisp.volvox.application.webmvc;

import static com.github.aetherwisp.volvox.Volvox.Profiles.DEV;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(DEV)
public class DevelopWebMvcConfigurer extends AbstractWebMvcConfigurer {

    public DevelopWebMvcConfigurer() {
        super("file:src/main/resources/templates/", "file:src/main/resources/static/");
    }
}
