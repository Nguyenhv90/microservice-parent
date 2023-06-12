package com.hvn.bookservice.query.queries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBooksQuery {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
