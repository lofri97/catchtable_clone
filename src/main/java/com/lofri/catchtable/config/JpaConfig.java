package com.lofri.catchtable.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaConfig {

    private final EntityManager entityManager;

    @Bean
    JPAQueryFactory jpaQueryFactory() {
        // Todo JpaQueryFactory auditing 관련 override update custom class 생성 반환
        return new JPAQueryFactory(entityManager);
    }
}
