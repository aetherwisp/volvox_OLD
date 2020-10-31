package com.github.aetherwisp.volvox.application.index;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    //======================================================================
    // Methods
    @GetMapping(path = {"/index"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    //    @PostMapping(path = {"/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    //    public ResponseEntity<String> post(@RequestParam("username") String _userName, @RequestParam("password") String _password) {
    //        return ResponseEntity.status(HttpStatus.SEE_OTHER)
    //                .header(HttpHeaders.LOCATION, "/main")
    //                .body(null);
    //    }
}
