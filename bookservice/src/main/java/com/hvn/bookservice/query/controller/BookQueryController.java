package com.hvn.bookservice.query.controller;

import com.hvn.bookservice.query.model.BookResponseModel;
import com.hvn.bookservice.query.queries.GetBooksQuery;
import com.hvn.bookservice.query.queries.SearchBooksQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId) {
        GetBooksQuery getBooksQuery = new GetBooksQuery();
        getBooksQuery.setBookId(bookId);

        BookResponseModel bookResponseModel = queryGateway
                .query(getBooksQuery, ResponseTypes.instanceOf(BookResponseModel.class))
                .join();
        return bookResponseModel;
    }

    @GetMapping("/search")
    public List<BookResponseModel> searchBook(@RequestBody SearchBooksQuery searchBooksQuery) {

        List<BookResponseModel> books = queryGateway
                .query(searchBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                .join();
        return books;
    }
}
