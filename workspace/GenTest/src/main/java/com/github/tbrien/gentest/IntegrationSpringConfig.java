package com.github.tbrien.gentest;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mongodb.Mongo;

/**
 * Only loaded when profile is set to "integration"
 */
@Configuration
@Profile("integration")
public class IntegrationSpringConfig {

//    @Bean
//    public RecommendationFacade recommendationFacade() {
//        return new RecommendationFacadeMongo();
//    }

//    @Bean
//    public Datastore datastore() throws MongoException, UnknownHostException {
//        Morphia morphia = new Morphia();
//
//        morphia.map(DBAction.class);
//
//        return morphia.createDatastore(mongo(), DB_NAME);
//    }

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new Mongo();
    }
}
