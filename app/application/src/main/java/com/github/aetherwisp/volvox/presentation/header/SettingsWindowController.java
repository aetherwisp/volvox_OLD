package com.github.aetherwisp.volvox.presentation.header;

import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/header/settings")
public class SettingsWindowController {
    //======================================================================
    // Fields
    private static final Logger logger = getLogger(lookup().lookupClass());

    //======================================================================
    // Methods
    @GetMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SettingsWindowMenu> menus() {

        // FIXME: 暫定コード
        return Arrays.asList(new SettingsWindowMenu("Sample Group", "Sample", "https://www.google.co.jp/"),
                new SettingsWindowMenu("System", "JVM Monitor", "https://www.google.co.jp/"),
                new SettingsWindowMenu("System", "Logging", "https://www.google.co.jp/"));
    }
}
