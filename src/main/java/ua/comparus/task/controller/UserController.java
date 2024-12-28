package ua.comparus.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.comparus.task.dto.UserDto;
import ua.comparus.task.service.UserService;

/**
 * REST controller for user operations.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to users")
public class UserController {

    @NonNull
    private final UserService userService;

    /**
     * Retrieves all users.
     *
     * @return a list of all users.
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all users from the system.")
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved list of users",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
    )
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }
}
