package ua.comparus.task.controller;

import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.comparus.task.dto.UserDto;
import ua.comparus.task.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testFindAllUsers() {
        var user1 = new UserDto("1", "jdoe", "John", "Doe");
        var user2 = new UserDto("2", "asmith", "Alice", "Smith");
        var users = List.of(user1, user2);

        when(userService.findAllUsers()).thenReturn(users);

        userController.findAllUsers();

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void testFindAllUsers_EmptyList() {
        when(userService.findAllUsers()).thenReturn(List.of());

        userController.findAllUsers();

        verify(userService, times(1)).findAllUsers();
    }
}
