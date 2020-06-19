package com.github.aetherwisp.volvox.infrastructure.user;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

// @SpringBootTest(classes = JdbcUserRepository.class)
@SpringJUnitConfig(JdbcUserRepository.class)
@ActiveProfiles("test")
//@Import(JdbcUserRepository.class)
public class JdbcUserRepositoryTest {
    private final JdbcUserRepository userRepository;

    @Autowired
    public JdbcUserRepositoryTest(final JdbcUserRepository _userRepository) {
        this.userRepository = Objects.requireNonNull(_userRepository);
    }


    @Test
    public void test() {

    }
}
