package com.github.aetherwisp.volvox.application.webmvc;

import static com.github.aetherwisp.volvox.Volvox.Profiles.PRD;
import static com.github.aetherwisp.volvox.Volvox.Profiles.STG;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({STG, PRD})
public class ProductWebMvcConfigurer extends AbstractWebMvcConfigurer {

    public ProductWebMvcConfigurer() {
        super("classpath:templates/", "file:static/");
    }
}
