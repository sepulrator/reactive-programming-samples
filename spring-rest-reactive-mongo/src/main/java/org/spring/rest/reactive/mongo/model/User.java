package org.spring.rest.reactive.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class User {
    @Id
    @NonNull String id;
    @NonNull String name;
    @NonNull int age;


}
