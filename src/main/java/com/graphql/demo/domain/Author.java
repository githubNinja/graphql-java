package com.graphql.demo.domain;


import lombok.Data;

@Data(staticConstructor = "of")
public class Author {
    private final int id;
    private final String firstName;
    private final String lastName;
}
