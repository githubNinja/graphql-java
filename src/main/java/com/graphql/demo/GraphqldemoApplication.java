package com.graphql.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphqldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( GraphqldemoApplication.class, args );
    }

	/*@Bean
	GraphQLSchema schema() {
		return GraphQLSchema.newSchema()
				.query( GraphQLObjectType.newObject()
						.name("query")
						.field(field -> field
								.name("test")
								.type( Scalars.GraphQLString)
								.dataFetcher(environment -> "response")
						)
						.build())
				.build();
	}*/

}
