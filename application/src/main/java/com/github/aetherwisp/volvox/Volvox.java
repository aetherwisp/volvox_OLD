package com.github.aetherwisp.volvox;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Volvox {

    public static class Profiles {
        public static final String DEV = "dev";
        public static final String STG = "stg";
        public static final String PRD = "prd";
    }

    //======================================================================
    // Fields
    private final String version;

    private final String adminlte;

    //======================================================================
    // Constructors
    @Autowired
    public Volvox(@Value("${aetherwisp.volvox.application.version}") final String _version,
            @Value("${aetherwisp.volvox.application.adminlte}") final String _adminlte) {
        this.version = Objects.requireNonNull(_version);
        this.adminlte = Objects.requireNonNull(_adminlte);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }

    public String getAdminlte() {
        return this.adminlte;
    }
}
