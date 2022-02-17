package com.adeo.vault.client.config;

import com.adeo.vault.client.commons.DataSourceCredentials;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSourceConfig {

    private final DataSourceCredentials credentials;

    @Bean
    public DataSource getDataSource() {
        log.info("username = {}, password={}, url:{}",credentials.getUsername(),credentials.getPassword(),credentials.getUrl());
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(credentials.getUrl());
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());
        config.setPoolName("postgresql-pool");

        return new HikariDataSource(config);
    }

}
