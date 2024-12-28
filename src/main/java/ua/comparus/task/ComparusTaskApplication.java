package ua.comparus.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main application class for the Comparus Task application.
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ComparusTaskApplication {

    /**
     * Main method to launch the application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ComparusTaskApplication.class, args);
    }
}
