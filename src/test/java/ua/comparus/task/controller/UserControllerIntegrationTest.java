package ua.comparus.task.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class UserControllerIntegrationTest {

    @Container
    static MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:9.1")
        .withInitScript("db1-init.sql");

    @Container
    static PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>("postgres:17.2")
        .withInitScript("db2-init.sql");

    @Autowired
    private MockMvc mockMvc;

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("app.data-sources[0].name", () -> "db1");
        registry.add("app.data-sources[0].driver", mySqlContainer::getDriverClassName);
        registry.add("app.data-sources[0].url", mySqlContainer::getJdbcUrl);
        registry.add("app.data-sources[0].user", mySqlContainer::getUsername);
        registry.add("app.data-sources[0].password", mySqlContainer::getPassword);
        registry.add("app.data-sources[0].table", () -> "users");
        registry.add("app.data-sources[0].mapping.id", () -> "user_id");
        registry.add("app.data-sources[0].mapping.username", () -> "login");
        registry.add("app.data-sources[0].mapping.name", () -> "first_name");
        registry.add("app.data-sources[0].mapping.surname", () -> "last_name");
        registry.add("app.data-sources[1].name", () -> "db2");
        registry.add("app.data-sources[1].driver", postgreSqlContainer::getDriverClassName);
        registry.add("app.data-sources[1].url", postgreSqlContainer::getJdbcUrl);
        registry.add("app.data-sources[1].user", postgreSqlContainer::getUsername);
        registry.add("app.data-sources[1].password", postgreSqlContainer::getPassword);
        registry.add("app.data-sources[1].table", () -> "user_table");
        registry.add("app.data-sources[1].mapping.id", () -> "ldap_login");
        registry.add("app.data-sources[1].mapping.username", () -> "ldap_login");
        registry.add("app.data-sources[1].mapping.name", () -> "name");
        registry.add("app.data-sources[1].mapping.surname", () -> "surname");
    }

    @Test
    void testFindAllUsers() throws Exception {
        mockMvc
            .perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is("1")))
            .andExpect(jsonPath("$[0].username", is("jdoe")))
            .andExpect(jsonPath("$[0].name", is("John")))
            .andExpect(jsonPath("$[0].surname", is("Doe")))
            .andExpect(jsonPath("$[1].id", is("asmith")))
            .andExpect(jsonPath("$[1].username", is("asmith")))
            .andExpect(jsonPath("$[1].name", is("Alice")))
            .andExpect(jsonPath("$[1].surname", is("Smith")));
    }
}
