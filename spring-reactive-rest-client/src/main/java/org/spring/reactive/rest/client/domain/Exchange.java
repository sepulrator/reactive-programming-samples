package org.spring.reactive.rest.client.domain;

import lombok.Data;

import java.util.List;


@Data
public class Exchange {
    String base;
    String date;
    List<Currency> rate;

}
