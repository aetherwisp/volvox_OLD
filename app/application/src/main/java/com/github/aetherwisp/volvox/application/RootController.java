package com.github.aetherwisp.volvox.application;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {

    @GetMapping(path = {"/"})
    public RedirectView root() {
        final RedirectView view = new RedirectView("index", true);
        view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return view;
    }

    @GetMapping(path = {"/app/**"})
    public ModelAndView javascript(HttpServletRequest _request) {
        return new ModelAndView(_request.getRequestURI()
            .replace(_request.getContextPath(), "")
            .replaceFirst("^/", "")
            .replaceFirst("\\.js$", ""));
    }
}
