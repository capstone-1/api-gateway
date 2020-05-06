package com.capstone.web.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Configurable
public class GCStorage {
    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }
}
