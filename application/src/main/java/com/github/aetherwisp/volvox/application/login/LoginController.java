package com.github.aetherwisp.volvox.application.login;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping(path = { "/", "/login" }, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView get() {
        return new ModelAndView("login");
    }

    @PostMapping(path = { "/login" }, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView post() {
        return new ModelAndView("login");
    }
}
