package org.spring.rest.reactive.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.rest.reactive.mongo.model.User;
import org.spring.rest.reactive.mongo.repository.ReactiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveUserRepositoryIntegrationTest {

    @Autowired
    ReactiveUserRepository repository;
    @Autowired
    ReactiveMongoOperations operations;

    @Before
    public void setUp() {

        operations.collectionExists(User.class) //
                .flatMap(exists -> exists ? operations.dropCollection(User.class) : Mono.just(exists)) //
                .flatMap(o -> operations.createCollection(User.class)) //
                .then() //
                .block();


        repository
                .deleteAll()
                .subscribe(null, null, () -> {

                    Stream.of(new User("1",
                            "Peter", 12),new User("2",
                            "Sam", 26),new User("3",
                            "Ryan", 30),new User("4",
                            "Chris", 35)
                    )
                            .forEach(user -> {
                                repository
                                        .save(user)
                                        .subscribe(System.out::println);

                            });

                })
        ;
    }

    @Test
    public void shouldQueryDataWithAll() {

        List<User> whites = repository.findAll()
                .collectList() //
                .block();

        assertThat(whites).hasSize(4);
    }

    @Test
    public void shouldQueryDataWithId() {

        User user = repository.findById("1").block();

        assertThat(user).isNotNull();
    }

}
