package com.github.aetherwisp.volvox.presentation.header;

import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import org.apache.logging.log4j.Logger;
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
}
