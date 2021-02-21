package com.graphql.demo.com.graphql.datafetchers;

import com.graphql.demo.domain.Author;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class AuthorFetcher implements DataFetcher<Author> {

    @Override
    public Author get(DataFetchingEnvironment environment) {
        return getAuthor(environment);
    }

    private Author getAuthor(DataFetchingEnvironment environment) {

        return null;
    }
}
