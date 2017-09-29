package org.spring.rest.reactive.mongo;

import org.spring.rest.reactive.mongo.model.User;
import org.spring.rest.reactive.mongo.repository.ReactiveUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringRestReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestReactiveMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner saveUsers(ReactiveUserRepository reactiveUserRepository) {

		return args -> {
			reactiveUserRepository
					.deleteAll()
					.subscribe(null, null, () -> {

						Stream.of(new User(UUID.randomUUID().toString(),
								"Peter", 12),new User(UUID.randomUUID().toString(),
								"Sam", 26),new User(UUID.randomUUID().toString(),
								"Ryan", 30),new User(UUID.randomUUID().toString(),
								"Chris", 35)
						)
								.forEach(user -> {
									reactiveUserRepository
											.save(user)
											.subscribe(System.out::println);

								});

					})
			;
		};

	}

}
