package com.example.batch.config;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@TestConfiguration
public class JpaConfigTest {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("test");
    }
}