package com.github.aetherwisp.volvox.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {

    @GetMapping(path = {"/"})
    public RedirectView root() {
        return new RedirectView("index", true);
    }
}
