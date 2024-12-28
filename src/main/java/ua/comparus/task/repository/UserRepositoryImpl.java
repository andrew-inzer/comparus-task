package ua.comparus.task.repository;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import ua.comparus.task.dto.UserDto;

/**
 * Implementation of UserRepository using JdbcClient.
 */
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_ALL_USERS_QUERY_TEMPLATE = "SELECT %s FROM %s";
    private static final String MAPPING_STATEMENT_TEMPLATE = "%s AS %s";
    private static final String MAPPING_STATEMENTS_DELIMITER = ", ";

    @NonNull
    private final JdbcClient jdbcClient;

    @NonNull
    private final String findAllUsersQuery;

    /**
     * Constructs a UserRepositoryImpl instance.
     *
     * @param jdbcClient the JDBC client.
     * @param table      the table name.
     * @param mapping    the column-to-field mapping.
     */
    public UserRepositoryImpl(
        @NonNull JdbcClient jdbcClient,
        @NonNull String table,
        @NonNull Map<String, String> mapping
    ) {
        this.jdbcClient = jdbcClient;
        this.findAllUsersQuery = generateFindAllUsersQuery(table, mapping);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users.
     */
    @Override
    public List<UserDto> findAllUsers() {
        return jdbcClient
            .sql(findAllUsersQuery)
            .query(UserDto.class)
            .list();
    }

    private String generateFindAllUsersQuery(String table, Map<String, String> mapping) {
        var mappingStatement = mapping
            .entrySet()
            .stream()
            .map(entry -> MAPPING_STATEMENT_TEMPLATE.formatted(entry.getValue(), entry.getKey()))
            .collect(joining(MAPPING_STATEMENTS_DELIMITER));

        return FIND_ALL_USERS_QUERY_TEMPLATE.formatted(mappingStatement, table);
    }
}
