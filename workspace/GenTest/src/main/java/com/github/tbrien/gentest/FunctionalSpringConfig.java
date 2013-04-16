package com.github.tbrien.gentest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * Only loaded when profile is set to "functional"
 * This profile is used for functional tests.
 */
@Configuration
@Profile("functional")
public class FunctionalSpringConfig {
//    @Bean
//    public RecommendationFacade recommendationFacade() {
//        return new RecommandationFacadeStub();
//    }
}
