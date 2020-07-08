package com.github.aetherwisp.volvox.application.user;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.aetherwisp.volvox.domain.user.User;
import com.github.aetherwisp.volvox.domain.user.UserRepository;

@RestController
public class UserController {
    //======================================================================
    // Fields
    private final UserRepository userRepository;

    //======================================================================
    // Constructors
    @Autowired
    private UserController(final UserRepository _userRepository) {
        this.userRepository = Objects.requireNonNull(_userRepository);
    }

    //======================================================================
    // Methods
    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@PathVariable("id") Integer _id) {
        // FIXME: 未実装
        throw new UnsupportedOperationException();
    }
}
