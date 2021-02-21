package com.graphql.demo.component;

import com.google.common.collect.ImmutableMap;
import com.graphql.demo.domain.Author;
import com.graphql.demo.domain.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class GraphQLDataFetchers {

    private List<Map<String, String>> books =
            Arrays.asList(ImmutableMap.of("id", "book-id123",
                    "name", "graphQL",
                    "pageCount", "200",
                    "authorId", "101"),
                    ImmutableMap.of("id", "book-id124",
                            "name", "hibernate",
                            "pageCount", "220",
                            "authorId", "102")
            );

    private List<Map<String, String>> authors =
            Arrays.asList(ImmutableMap.of("id1", "101",
                    "firstName", "Alex",
                    "lastName", "Banks"),
                    ImmutableMap.of("id1", "102",
                            "firstName", "Paul",
                            "lastName", "Simmons"));


    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            System.out.println("bookId is::" + bookId);
            return getBooksById(bookId);
        };
    }

    public Book getBookByIdGet(DataFetchingEnvironment dataFetchingEnvironment) {
        String bookId = dataFetchingEnvironment.getArgument("id");
        Map<String, String> booksById = getBooksById(bookId);
        return Book.of(booksById.get("id"), booksById.get("name"),
                Integer.valueOf(booksById.get("pageCount")),
                getAuthorDataFetcher(booksById.get("authorId")));

    }


    public Map<String, String> getBooksById(String bookId) {
        return books.stream()
                .filter(book -> book.get("id").equals(bookId))
                .findFirst()
                .orElse(null);
    }


    //This is implemented and works fine with the fetcher
    public Author getAuthorDataFetcher(String authorId) {
        Optional<Map<String, String>> authorDetails = authors.stream().filter(author ->
                author.get("id1").contains(authorId))
                .findFirst();
        Map<String, String> authorMap = authorDetails.get();
        return Author.of(Integer.valueOf(authorMap.get("id1")), authorMap.get("firstName"),
                authorMap.get("lastName"));
    }


}
