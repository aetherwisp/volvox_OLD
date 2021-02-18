package com.github.aetherwisp.volvox.presentation.header;

import static com.github.aetherwisp.volvox.presentation.MessageKeys.Header.Menu.SETTINGS_GENERAL;
import static com.github.aetherwisp.volvox.presentation.MessageKeys.Header.Menu.SETTINGS_LANGUAGE;
import static com.github.aetherwisp.volvox.presentation.MessageKeys.Header.Menu.SETTINGS_TIMEZONE;
import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import java.util.Locale;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/app/header")
public class SettingsWindowController {
    //======================================================================
    // Fields
    private static final Logger logger = getLogger(lookup().lookupClass());

    private final MessageSourceAccessor messageSource;

    //======================================================================
    // Constructors
    @Autowired
    public SettingsWindowController(final MessageSource _messageSource) {
        this.messageSource = new MessageSourceAccessor(Objects.requireNonNull(_messageSource));
    }

    //======================================================================
    // Methods
    @GetMapping(path = {"/SettingsWindow.js"})
    public ModelAndView javascript(final HttpServletRequest _request, final Locale _locale) {
        final Setting timezoneAndLanguage = new Setting(
                this.messageSource.getMessage(SETTINGS_TIMEZONE) + " / " + this.messageSource.getMessage(SETTINGS_LANGUAGE));
        final Setting general = new Setting(this.messageSource.getMessage(SETTINGS_GENERAL), timezoneAndLanguage);
        final Setting root = Setting.root(general);

        return new ModelAndView(_request.getRequestURI()
            .replace(_request.getContextPath(), "")
            .replaceFirst("^/", "")
            .replaceFirst("\\.js$", "")).addObject("root", root);
    }

    //    @GetMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    //    public List<SettingsWindowMenu> menus() {
    //
    //        // FIXME: 暫定コード
    //        return Arrays.asList(new SettingsWindowMenu("Sample Group", "Sample", "https://www.google.co.jp/"),
    //                new SettingsWindowMenu("System", "JVM Monitor", "https://www.google.co.jp/"),
    //                new SettingsWindowMenu("System", "Logging", "https://www.google.co.jp/"));
    //    }
}
