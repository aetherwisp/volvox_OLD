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

    private final ScriptVersions scripts;

    //======================================================================
    // Constructors
    @Autowired
    public VolvoxApplication(@Value("${" + VolvoxApplication.PREFIX + ".version}") final String _version, final ScriptVersions _scripts) {
        this.version = Objects.requireNonNull(_version);
        this.scripts = Objects.requireNonNull(_scripts);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }

    public ScriptVersions getScripts() {
        return this.scripts;
    }

    //======================================================================
    // Classes
    @Component
    @ConfigurationProperties(prefix = VolvoxApplication.PREFIX + ".scripts")
    public static class ScriptVersions {
        private static final String PREFIX = VolvoxApplication.PREFIX + ".scripts";

        private final String adminlte;

        private final String webfontloader;

        @Autowired
        public ScriptVersions(@Value("${" + ScriptVersions.PREFIX + ".adminlte}") final String adminlte,
                @Value("${" + ScriptVersions.PREFIX + ".webfontloader}") final String webfontloader) {
            this.adminlte = Objects.requireNonNull(adminlte);
            this.webfontloader = Objects.requireNonNull(webfontloader);
        }

        public String getAdminlte() {
            return this.adminlte;
        }

        public String getWebfontloader() {
            return this.webfontloader;
        }
    }
}