package ua.comparus.task.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.simple.JdbcClient;
import ua.comparus.task.dto.UserDto;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private JdbcClient jdbcClient;

    @Mock
    private JdbcClient.StatementSpec statementSpec;

    @Mock
    private JdbcClient.MappedQuerySpec<UserDto> mappedQuerySpec;

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        var mapping = Map.of("id", "user_id", "username", "login", "name", "first_name", "surname", "last_name");
        userRepository = new UserRepositoryImpl(jdbcClient, "users", mapping);

        when(jdbcClient.sql(anyString())).thenReturn(statementSpec);
        when(statementSpec.query(eq(UserDto.class))).thenReturn(mappedQuerySpec);
    }

    @Test
    void testFindAllUsers() {
        var user1 = new UserDto("1", "jdoe", "John", "Doe");
        var user2 = new UserDto("2", "asmith", "Alice", "Smith");
        var users = List.of(user1, user2);

        when(mappedQuerySpec.list()).thenReturn(users);

        var result = userRepository.findAllUsers();

        assertEquals(users, result);
        verify(jdbcClient, times(1)).sql(anyString());
    }

    @Test
    void testFindAllUsers_NoUsers() {
        when(mappedQuerySpec.list()).thenReturn(List.of());

        var result = userRepository.findAllUsers();

        assertEquals(0, result.size());
        verify(jdbcClient, times(1)).sql(anyString());
    }
}
