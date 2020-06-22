package com.github.aetherwisp.volvox;

import java.io.IOException;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

// @Configuration
// @ComponentScan({"com.github.aetherwisp.volvox"})
// @SpringBootConfiguration
// @EnableAutoConfiguration
@SpringBootApplication
public class VolvoxInfrastructureTestConfiguration {
    static {
        SpringApplication.run(VolvoxInfrastructureTestConfiguration.class, new String[0]);
    }

    //======================================================================
    // Methods
    public static void main(String[] _args) {
        SpringApplication.run(VolvoxInfrastructureTestConfiguration.class, _args);
    }

    //======================================================================
    // Components
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSourceProperties defaultDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource defaultDataSource(DataSourceProperties _defaultDataSourceProperties) {
        return _defaultDataSourceProperties.initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public MessageSource messageSource() throws IOException {
        return new ResourceBundleMessageSource();
    }
}
