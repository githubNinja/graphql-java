package com.graphql.demo.domain;


import lombok.Data;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/
@Data(staticConstructor = "of")
public class Book {
    private final String id;
    private final String name;
    private final int pageCount;
    private final Author author;
}
