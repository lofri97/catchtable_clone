package com.lofri.catchtable.test.config;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.lofri.catchtable.common.exception.ResponseCodeResolver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@TestConfiguration
public class RestDocsConfiguration {

//    @Bean
//    RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
//        return configurer -> configurer.operationPreprocessors()
//                .withRequestDefaults(prettyPrint())
//                .withResponseDefaults(prettyPrint());
//    }

    @Bean
    public RestDocumentationResultHandler restDocumentationResultHandler() {
        return MockMvcRestDocumentationWrapper.document(
                "{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );
    }

    @Bean
    public ResponseCodeResolver responseCodeResolver() {
        return new ResponseCodeResolver();
    }
}
