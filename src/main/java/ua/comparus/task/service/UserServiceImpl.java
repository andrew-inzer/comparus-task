package ua.comparus.task.service;

import java.util.Collection;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.comparus.task.dto.UserDto;
import ua.comparus.task.repository.UserRepository;

/**
 * Implementation of UserService.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private final List<UserRepository> userRepositories;

    /**
     * Retrieves all users from all repositories.
     *
     * @return a list of all users.
     */
    @Override
    public List<UserDto> findAllUsers() {
        return userRepositories
            .stream()
            .map(UserRepository::findAllUsers)
            .flatMap(Collection::stream)
            .toList();
    }
}
