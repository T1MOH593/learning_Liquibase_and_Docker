package by.vlad.liquibase_starter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("belarusbank.api")
@Configuration
public class ServiceConfiguration {
    private String url;
}

