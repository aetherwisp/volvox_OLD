package com.github.aetherwisp.volvox.application.login;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    //======================================================================
    // Methods
    @GetMapping(path = {"/", "/login"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView get() {
        return new ModelAndView("login");
    }

    @PostMapping(path = {"/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestParam("username") String _userName, @RequestParam("password") String _password) {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header(HttpHeaders.LOCATION, "/main")
                .body(null);
    }

}
