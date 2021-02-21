package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.demo.com.graphql.datafetchers.BookFetcher;
import com.graphql.demo.domain.Book;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BookFetcher bookFetcher;

    public Book getBookById(String bookId, DataFetchingEnvironment environment) {
        return bookFetcher.getBook( environment );
    }
}
