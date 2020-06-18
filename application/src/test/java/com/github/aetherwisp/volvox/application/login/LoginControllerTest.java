package com.github.aetherwisp.volvox.application.login;

import java.util.Objects;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class LoginControllerTest {
    //======================================================================
    // Fields
    final MockMvc mockMvc;

    final DataSource dataSource;

    final ObjectMapper mapper = new ObjectMapper();

    //======================================================================
    // Constructors
    @Autowired
    public LoginControllerTest(final MockMvc _mockMvc, final DataSource _dataSource) {
        this.mockMvc = Objects.requireNonNull(_mockMvc);
        this.dataSource = Objects.requireNonNull(_dataSource);
    }


    //======================================================================
    // Tests
    @Nested
    @DisplayName("ログイン画面")
    public class LoginTest {

        @Test
        public void test() throws Exception {
            Assertions.assertNotNull(mockMvc);
        }
    }
}
