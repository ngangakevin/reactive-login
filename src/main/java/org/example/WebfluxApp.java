package org.example;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Mono;


import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import static java.lang.Integer.parseInt;

@SpringBootApplication
@EnableConfigurationProperties
@EnableR2dbcRepositories()
@EnableScheduling
public class WebfluxApp {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxApp.class, args);
    }
}
