package by.vlad.liquibase_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableMongoRepositories
public class LiquibaseStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseStarterApplication.class, args);
    }

}
