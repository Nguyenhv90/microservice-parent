package com.hvn.bookservice.query.projecttion;

import com.hvn.bookservice.command.data.Book;
import com.hvn.bookservice.command.data.BookRepository;
import com.hvn.bookservice.query.model.BookResponseModel;
import com.hvn.bookservice.query.queries.GetBooksQuery;
import com.hvn.bookservice.query.queries.SearchBooksQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {
    private final BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBooksQuery getBooksQuery) {
        BookResponseModel model = new BookResponseModel();
        Book book = bookRepository.getBookByBookId(getBooksQuery.getBookId());
        BeanUtils.copyProperties(book, model);
        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(SearchBooksQuery query) {
        List<BookResponseModel> modelList = new ArrayList<>();
        List<Book> books = bookRepository.findAll();

        books.stream().forEach(s -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(s, model);
            modelList.add(model);
        });

        return modelList;
    }

}
