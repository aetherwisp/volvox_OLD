package com.github.aetherwisp.volvox.infrastructure;

import static com.github.aetherwisp.volvox.Volvox.Profiles.*;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({DEV, STG, PRD})
public class VolvoxDataSourceConfiguration {
    //======================================================================
    // Constants
    private static final String PREFIX = VolvoxInfrastructure.PREFIX + ".datasource";

    //======================================================================
    // DataSourceProperties
    @Bean
    @ConfigurationProperties(prefix = PREFIX + ".default")
    @Primary
    public DataSourceProperties defaultDataSourceProperties() {
        return new DataSourceProperties();
    }

    //======================================================================
    // DataSources
    @Bean
    @Primary
    public DataSource defaultDataSource(DataSourceProperties _defaultDataSourceProperties) {
        return _defaultDataSourceProperties.initializeDataSourceBuilder()
                .build();
    }
}
