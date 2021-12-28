package by.vlad.liquibase_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LiquibaseStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseStarterApplication.class, args);
    }

}
