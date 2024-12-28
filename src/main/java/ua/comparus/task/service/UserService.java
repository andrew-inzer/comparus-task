package ua.comparus.task.service;

import java.util.List;
import ua.comparus.task.dto.UserDto;

/**
 * Service interface for user operations.
 */
public interface UserService {
    /**
     * Finds all users.
     *
     * @return a list of all users.
     */
    List<UserDto> findAllUsers();
}
