package ua.comparus.task.config;

import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Comparus Task application.
 *
 * @param dataSources a list of data source configurations.
 */
@ConfigurationProperties(prefix = "app")
public record ComparusTaskApplicationProperties(@NonNull List<DataSource> dataSources) {
    /**
     * Data source configuration.
     *
     * @param name     the name of the data source.
     * @param driver   the JDBC driver class name.
     * @param url      the JDBC URL.
     * @param user     the username for the database.
     * @param password the password for the database.
     * @param table    the table name.
     * @param mapping  the column-to-field mapping.
     */
    public record DataSource(
        @NonNull String name,
        @NonNull String driver,
        @NonNull String url,
        @NonNull String user,
        @NonNull String password,
        @NonNull String table,
        @NonNull Map<String, String> mapping
    ) {
    }
}
