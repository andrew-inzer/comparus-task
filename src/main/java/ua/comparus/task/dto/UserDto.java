package ua.comparus.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

/**
 * Data Transfer Object (DTO) representing a user.
 *
 * @param id       the user ID.
 * @param username the username.
 * @param name     the user's first name.
 * @param surname  the user's last name.
 */
@Schema(description = "Data Transfer Object representing a user")
public record UserDto(
    @NonNull
    @Schema(description = "The user ID", example = "12345")
    String id,

    @NonNull
    @Schema(description = "The username", example = "jdoe")
    String username,

    @NonNull
    @Schema(description = "The user's first name", example = "John")
    String name,

    @NonNull
    @Schema(description = "The user's last name", example = "Doe")
    String surname
) {
}
