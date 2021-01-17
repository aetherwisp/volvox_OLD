package com.github.aetherwisp.volvox.application.dashboard;

import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    //======================================================================
    // Fields
    private static final Logger logger = getLogger(lookup().lookupClass());

    //======================================================================
    // Methods
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView dashboard() {
        return new DashboardView();
    }

    @GetMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> menus() {
        final Object principal = SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        if (principal instanceof UserDetails) {
            logger.debug("username: {}", ((UserDetails) principal).getUsername());
        } else {
            logger.debug("username: {}", principal.toString());
        }



        // FIXME: 暫定コード
        return Arrays.asList(new Menu("Sample Group", "Sample", "https://www.google.co.jp/"),
                new Menu("System", "JVM Monitor", "https://www.google.co.jp/"),
                new Menu("System", "Logging", "https://www.google.co.jp/"));
    }


    //======================================================================
    // Classes
    private static class DashboardView extends ModelAndView {
        private DashboardView() {
            super("dashboard");
        }
    }

}
