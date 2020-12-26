package com.github.aetherwisp.volvox.application.dashboard;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DashboardController {

    //======================================================================
    // Methods
    @GetMapping(path = {"/dashboard"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView dashboard() {
        return new DashboardView();
    }

    //======================================================================
    // Classes
    private static class DashboardView extends ModelAndView {
        private DashboardView() {
            super("dashboard");
        }
    }

}
