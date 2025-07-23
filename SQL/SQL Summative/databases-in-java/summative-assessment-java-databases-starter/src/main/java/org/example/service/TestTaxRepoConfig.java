package org.example.service;

import org.example.data.impl.TaxRepoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class TestTaxRepoConfig {

    @Bean
    public TaxRepoImpl taxRepo(JdbcTemplate jdbcTemplate) {
        return new TaxRepoImpl(jdbcTemplate);
    }
}
