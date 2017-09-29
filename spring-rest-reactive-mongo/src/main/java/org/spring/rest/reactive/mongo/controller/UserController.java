package org.spring.rest.reactive.mongo.controller;

import org.spring.rest.reactive.mongo.model.User;
import org.spring.rest.reactive.mongo.repository.ReactiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ReactiveUserRepository reactiveUserRepository;


    @GetMapping
    public Flux<User> getAllUser() {
        return reactiveUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable("id") String id) {
        return reactiveUserRepository.findById(id);
    }











}
