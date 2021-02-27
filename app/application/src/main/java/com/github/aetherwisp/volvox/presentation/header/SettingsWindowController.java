package com.github.aetherwisp.volvox.presentation.header;

import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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

    private static final Pattern timezoneIdPattern = Pattern.compile("^[^/]+/[^/]+$");

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
        final Map<String, String> timezoneValueMap = Arrays.stream(TimeZone.getAvailableIDs())
            .filter(ID -> 3 < ID.length())
            .filter(ID -> !ID.startsWith("Etc") || !ID.startsWith("SystemV") || !ID.startsWith("US"))
            .filter(ID -> timezoneIdPattern.matcher(ID)
                .matches())
            .map(ID -> TimeZone.getTimeZone(ID))
            .sorted(Comparator.comparing(TimeZone::getRawOffset, Comparator.reverseOrder()))
            .peek(tz -> logger.debug("({}){}: {}", Integer.valueOf(tz.getRawOffset() / (60 * 60 * 1000)), tz.getID(),
                    tz.getDisplayName()))
            .collect(Collectors.toMap(TimeZone::getDisplayName, TimeZone::getID, (v1, v2) -> v2, LinkedHashMap::new))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (v1, v2) -> v2, LinkedHashMap::new));

        logger.debug("----------");
        ZoneId.getAvailableZoneIds()
            .forEach(id -> logger.debug(ZoneId.of(id)));
        logger.debug("ゾーン総数：{}", Integer.valueOf(ZoneId.getAvailableZoneIds()
            .size()));

        return new ModelAndView(_request.getRequestURI()
            .replace(_request.getContextPath(), "")
            .replaceFirst("^/", "")
            .replaceFirst("\\.js$", "")).addObject("timezoneValueMap", timezoneValueMap);
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
