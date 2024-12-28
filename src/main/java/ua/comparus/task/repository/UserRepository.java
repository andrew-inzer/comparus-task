package ua.comparus.task.repository;

import java.util.List;
import ua.comparus.task.dto.UserDto;

/**
 * Repository interface for user operations.
 */
public interface UserRepository {
    /**
     * Finds all users in the repository.
     *
     * @return a list of all users.
     */
    List<UserDto> findAllUsers();
}
