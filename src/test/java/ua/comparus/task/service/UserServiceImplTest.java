package ua.comparus.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.comparus.task.dto.UserDto;
import ua.comparus.task.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository1;

    @Mock
    private UserRepository userRepository2;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(List.of(userRepository1, userRepository2));
    }

    @Test
    void testFindAllUsers() {
        var user1 = new UserDto("1", "jdoe", "John", "Doe");
        var user2 = new UserDto("2", "asmith", "Alice", "Smith");

        when(userRepository1.findAllUsers()).thenReturn(List.of(user1));
        when(userRepository2.findAllUsers()).thenReturn(List.of(user2));

        var result = userService.findAllUsers();

        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userRepository1, times(1)).findAllUsers();
        verify(userRepository2, times(1)).findAllUsers();
    }

    @Test
    void testFindAllUsers_EmptyRepositories() {
        when(userRepository1.findAllUsers()).thenReturn(List.of());
        when(userRepository2.findAllUsers()).thenReturn(List.of());

        var result = userService.findAllUsers();

        assertEquals(0, result.size());

        verify(userRepository1, times(1)).findAllUsers();
        verify(userRepository2, times(1)).findAllUsers();
    }
}
