package com.github.aetherwisp.volvox.webjars.smartclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.github.aetherwisp.volvox.domain.context.YamlPropertySourceFactory;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class,
        value = "classpath:config/volvox-webjars-smartclient.yml")
public class SmartClientConfiguration {
}
