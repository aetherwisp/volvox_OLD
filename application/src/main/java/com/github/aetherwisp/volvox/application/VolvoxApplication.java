package com.github.aetherwisp.volvox.application;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("volvox")
public class VolvoxApplication {
    //======================================================================
    // Constants
    public static final String PREFIX = "aetherwisp.volvox.application";

    //======================================================================
    // Fields
    private final String version;

    private final WebJars webjars;

    //======================================================================
    // Constructors
    @Autowired
    public VolvoxApplication(@Value("${" + VolvoxApplication.PREFIX + ".version}") final String _version, final WebJars _webjars) {
        this.version = Objects.requireNonNull(_version);
        this.webjars = Objects.requireNonNull(_webjars);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }

    public WebJars getWebjars() {
        return this.webjars;
    }

    //======================================================================
    // Classes
    @Component
    @ConfigurationProperties(prefix = VolvoxApplication.PREFIX + ".webjars")
    public static class WebJars {
        //======================================================================
        // Constants
        private static final String PREFIX = VolvoxApplication.PREFIX + ".webjars";

        //======================================================================
        // Fields
        /** Bootstrap version. */
        private final String bootstrap;

        /** jQuery version. */
        private final String jquery;

        //======================================================================
        // Constructors
        @Autowired
        public WebJars(@Value("${" + WebJars.PREFIX + ".bootstrap}") final String _bootstrap, @Value("${" + WebJars.PREFIX + ".jquery}") final String _jquery) {
            this.bootstrap = Objects.requireNonNull(_bootstrap);
            this.jquery = Objects.requireNonNull(_jquery);
        }

        //======================================================================
        // Getters
        public String getBootstrap() {
            return this.bootstrap;
        }

        public String getJquery() {
            return this.jquery;
        }
    }
}
