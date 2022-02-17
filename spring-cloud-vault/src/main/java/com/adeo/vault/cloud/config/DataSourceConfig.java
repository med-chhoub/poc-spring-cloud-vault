package com.adeo.vault.cloud.config;

import com.adeo.vault.cloud.commons.DataSourceDTO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.vault.core.VaultVersionedKeyValueTemplate;
import org.springframework.vault.support.Versioned;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSourceConfig {

    private String DATASOURCE_PATH="/apps/";

    private final VaultVersionedKeyValueTemplate vaultTemplate;
    private final Environment environment;

    @Value("${app.usage}")
    private String app;

    @Value("${app.env}")
    private String env;

    @Bean
   public DataSource getDataSource() {
        DATASOURCE_PATH += app + "-sm/datasource/postgresql/"+ env +"cloudgcp";
        Versioned<DataSourceDTO> response = vaultTemplate.get(DATASOURCE_PATH, DataSourceDTO.class);

        if(response != null && response.getData() != null){
            log.info("username = {}, password={}, url:{}",response.getData().getUsername(),response.getData().getPassword(),response.getData().getUrl());
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(response.getData().getUrl());
            config.setUsername(response.getData().getUsername());
            config.setPassword(response.getData().getPassword());
            config.setPoolName("postgresql-pool");

            return new HikariDataSource(config);
        }
        return  null;
    }
}
