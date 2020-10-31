package com.github.aetherwisp.volvox.application;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.aetherwisp.volvox.Volvox;

@Component("volvox")
public class VolvoxApplicationConfiguration {
    //======================================================================
    // Constants
    public static final String PREFIX = Volvox.CONFIG_ROOT + ".application";

    //======================================================================
    // Fields
    private final String version;

    //======================================================================
    // Constructors
    @Autowired
    public VolvoxApplicationConfiguration(@Value("${" + VolvoxApplicationConfiguration.PREFIX
            + ".version}") final String _version) {
        this.version = Objects.requireNonNull(_version);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }
}
