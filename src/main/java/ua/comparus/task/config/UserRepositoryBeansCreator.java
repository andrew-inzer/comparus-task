package ua.comparus.task.config;

import javax.sql.DataSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.simple.JdbcClient;
import ua.comparus.task.repository.UserRepository;
import ua.comparus.task.repository.UserRepositoryImpl;

/**
 * Class responsible for dynamically registering user repository beans.
 */
@RequiredArgsConstructor
public class UserRepositoryBeansCreator implements BeanDefinitionRegistryPostProcessor {

    @NonNull
    private final Environment environment;

    /**
     * Registers user repository beans based on configuration properties.
     *
     * @param registry the bean definition registry.
     * @throws BeansException in case of errors.
     */
    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        Binder.get(environment)
            .bind("app", Bindable.of(ComparusTaskApplicationProperties.class))
            .get()
            .dataSources()
            .forEach(dataSource -> registerUserRepositoryBean(registry, dataSource));
    }

    private void registerUserRepositoryBean(
        BeanDefinitionRegistry registry,
        ComparusTaskApplicationProperties.DataSource dataSource
    ) {
        var beanName = dataSource.name() + "UserRepository";
        var beanDefinition = BeanDefinitionBuilder
            .genericBeanDefinition(
                UserRepository.class,
                () -> new UserRepositoryImpl(createJdbcClient(dataSource), dataSource.table(), dataSource.mapping())
            )
            .getBeanDefinition();

        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private JdbcClient createJdbcClient(ComparusTaskApplicationProperties.DataSource dataSource) {
        return JdbcClient.create(createDataSource(dataSource));
    }

    private DataSource createDataSource(ComparusTaskApplicationProperties.DataSource dataSource) {
        return DataSourceBuilder.create()
            .driverClassName(dataSource.driver())
            .url(dataSource.url())
            .username(dataSource.user())
            .password(dataSource.password())
            .build();
    }
}
