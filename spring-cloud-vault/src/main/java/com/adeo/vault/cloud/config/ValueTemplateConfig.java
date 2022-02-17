package com.adeo.vault.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.core.VaultVersionedKeyValueTemplate;

@Configuration
public class ValueTemplateConfig {

    private final String VAULT_PATH_PREFIX="secret";

    @Bean
    public VaultVersionedKeyValueTemplate ValueTemplateConfig(VaultOperations vaultOperations) {
        return new VaultVersionedKeyValueTemplate(vaultOperations, VAULT_PATH_PREFIX);
    }
}
