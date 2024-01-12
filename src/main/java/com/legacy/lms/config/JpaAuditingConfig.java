package com.legacy.lms.config;

import com.legacy.lms.entity.User;
import com.legacy.lms.util.helper.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> Optional.ofNullable(Context.getCurrentUser());
    }
}
