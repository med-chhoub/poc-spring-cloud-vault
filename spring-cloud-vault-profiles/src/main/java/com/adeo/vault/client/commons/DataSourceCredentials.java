package com.adeo.vault.client.commons;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.annotation.VaultPropertySource;

@Data
@Configuration
@VaultPropertySource(value = "secret/apps/${app.usage}-sm/datasource/postgresql/${app.env}cloudgcp")
public class DataSourceCredentials {

    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
}
