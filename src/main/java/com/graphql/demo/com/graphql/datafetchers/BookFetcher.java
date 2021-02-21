package com.graphql.demo.com.graphql.datafetchers;

import com.graphql.demo.component.GraphQLDataFetchers;
import com.graphql.demo.domain.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookFetcher implements DataFetcher<Book> {


    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;

    @Override
    public Book get(DataFetchingEnvironment environment) {
        return getBook( environment );
    }

    public Book getBook(DataFetchingEnvironment env) {
        return graphQLDataFetchers.getBookByIdGet( env );

    }

}
