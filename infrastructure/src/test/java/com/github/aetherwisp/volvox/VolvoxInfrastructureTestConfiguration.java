package com.github.aetherwisp.volvox;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

// @Configuration
// @ComponentScan({"com.github.aetherwisp.volvox"})
@SpringBootConfiguration
@EnableAutoConfiguration
public class VolvoxInfrastructureTestConfiguration {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        return new ResourceBundleMessageSource();
    }
    //======================================================================
    // PropertySource Configurations
    //    @Configuration
    //    @PropertySource(factory = YamlPropertySourceFactory.class,
    //            value = {"classpath:config/volvox-infrastructure.yml", "classpath:config/volvox-infrastructure-test.yml"}, ignoreResourceNotFound = true)
    //    @Profile("test")
    //    static class InfrastructureTestConfiguration {
    //        // do nothing.
    //    }
}
