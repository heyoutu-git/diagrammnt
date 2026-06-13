package com.diagrammnt.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DataSourceConfig {
    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.configure().directory(System.getProperty("user.dir")).filename("diagram.env").load();
        log.info("DataSource: {}:{}/{}", dotenv.get("DB_HOST"), dotenv.get("DB_PORT"), dotenv.get("DB_NAME"));
    }
}
