package ua.comparus.task.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class for creating application-specific beans.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Comparus Task",
        description = "A Spring Boot application for demonstrating multi-database integration.",
        version = "1.0.0"
    ),
    externalDocs = @ExternalDocumentation(
        description = "Project Repository",
        url = "https://github.com/andrew-inzer/comparus-task"
    )
)
public class ComparusTaskApplicationContext {

    /**
     * Creates a bean for UserRepositoryBeansCreator.
     *
     * @param environment the Spring Environment object.
     * @return a UserRepositoryBeansCreator instance.
     */
    @Bean
    UserRepositoryBeansCreator userRepositoryBeansCreator(Environment environment) {
        return new UserRepositoryBeansCreator(environment);
    }
}
