package org.spring.rest.reactive.mongo.repository;

import org.spring.rest.reactive.mongo.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveUserRepository extends ReactiveMongoRepository<User,String> {

}
