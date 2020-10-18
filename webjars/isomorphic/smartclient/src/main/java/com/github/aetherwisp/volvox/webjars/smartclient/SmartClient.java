package com.github.aetherwisp.volvox.webjars.smartclient;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.github.aetherwisp.volvox.Volvox;

@Component("smartclient")
public class SmartClient {
    //======================================================================
    // Constants
    public static final String PREFIX = Volvox.CONFIG_ROOT + ".webjars.smartclient";

    //======================================================================
    // Fields
    private final String version;

    //======================================================================
    // Constructors
    @Autowired
    public SmartClient(@Value("${" + SmartClient.PREFIX + ".version}") final String _version) {
        this.version = Objects.requireNonNull(_version);
    }

    //======================================================================
    // Getters
    public String getVersion() {
        return this.version;
    }
}
