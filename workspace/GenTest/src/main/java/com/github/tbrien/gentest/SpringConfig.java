package com.github.tbrien.gentest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class SpringConfig {

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
//        return morphia.createDatastore(new Mongo(), DB_NAME);
//    }
}
