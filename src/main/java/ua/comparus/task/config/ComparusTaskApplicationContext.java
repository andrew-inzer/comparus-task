package ua.comparus.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class for creating application-specific beans.
 */
@Configuration
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
