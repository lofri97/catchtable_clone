package com.lofri.catchtable.test.config;

import com.lofri.catchtable.common.exception.ResponseCodeResolver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockMvcConfig {

    @Bean
    public ResponseCodeResolver responseCodeResolver() {
        return new ResponseCodeResolver();
    }
}
