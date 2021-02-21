package com.graphql.demo.component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.graphql.demo.com.graphql.datafetchers.AuthorFetcher;
import com.graphql.demo.com.graphql.datafetchers.BookFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static com.google.common.io.Resources.getResource;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {
        private GraphQL graphQL;

    @Autowired
    private BookFetcher bookFetcher;

    @Autowired
    private AuthorFetcher authorFetcher;


    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL resource = getResource( "schema.graphqls" );
        String urlString = Resources.toString( resource, Charsets.UTF_8 );
        GraphQLSchema graphQLSchema = buildSchema( urlString );
        this.graphQL = GraphQL.newGraphQL( graphQLSchema ).build();

    }

    private GraphQLSchema buildSchema(String urlString) {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse( urlString );
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema( typeDefinitionRegistry, buildWiring() );
    }

    private RuntimeWiring buildWiring() {
        System.out.println( "buildWiring !!" );
        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring()
                .type( newTypeWiring( "Query" )
                        .dataFetcher( "bookById", bookFetcher ) )
                .type( newTypeWiring( "Book" )
                        .dataFetcher( "author",
                                authorFetcher ) );
         /* .type( newTypeWiring("newQuery")
                        .typeResolver( bookQueryResolver).build());*/
        return builder.build();
    }

}
