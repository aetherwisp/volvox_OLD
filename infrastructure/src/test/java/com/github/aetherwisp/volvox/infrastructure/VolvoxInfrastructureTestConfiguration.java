package com.github.aetherwisp.volvox.infrastructure;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import com.github.aetherwisp.volvox.domain.context.YamlPropertySourceFactory;

@Configuration
public class VolvoxInfrastructureTestConfiguration {
    //======================================================================
    // PropertySource Configurations
    @Configuration
    @PropertySource(factory = YamlPropertySourceFactory.class,
            value = {"classpath:config/volvox-infrastructure.yml", "classpath:config/volvox-infrastructure-test.yml"}, ignoreResourceNotFound = true)
    @Profile("test")
    static class DevelopConfiguration {
        // do nothing.
    }

    //======================================================================
    // Fields
    private final ResourceBundleMessageSource messageSource;

    //======================================================================
    // Constructors
    public VolvoxInfrastructureTestConfiguration(final ResourceBundleMessageSource _messageSource) {
        this.messageSource = Objects.requireNonNull(_messageSource);
    }

    //======================================================================
    // Methods
    /**
     * Add volvox-infrastructure specific message source.
     */
    @PostConstruct
    public void initialize() {
        this.messageSource.addBasenames("i18n/volvox-infrastructure-messages");
    }

}
