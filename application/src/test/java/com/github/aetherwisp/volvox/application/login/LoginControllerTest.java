package com.github.aetherwisp.volvox.application.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.aetherwisp.volvox.test.CommonOperations;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

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
        @BeforeEach
        public void setUp() {
            //======================================================================
            // テストデータ登録
            final LocalDateTime sysdate = LocalDateTime.now();
            final Operation operation = Operations.sequenceOf(CommonOperations.DELETE_ALL_TABLES, Operations.insertInto("user")
                    .columns("name", "locked", "expired_at", "enabled", "created_at", "created_by", "updated_at", "updated_by")
                    .values("テストユーザ", Boolean.FALSE, sysdate.plusDays(1L), Boolean.TRUE, sysdate, Integer.valueOf(1), sysdate, Integer.valueOf(1))
                    .build());
            final DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
            dbSetup.launch();
        }

        @Test
        @DisplayName("初期表示")
        public void showPage() throws Exception {
            mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(content().encoding(StandardCharsets.UTF_8.name()))
                    .andExpect(view().name("login"));

        }
    }
}
