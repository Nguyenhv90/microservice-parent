package com.hvn.bookservice.command.event;

import com.hvn.bookservice.command.data.Book;
import com.hvn.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventsHandler {
    private final BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }
    @EventHandler
    public void on(BookUpdatedEvent event) {
        Book book = bookRepository.getBookByBookId(event.getBookId());
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        Book book = bookRepository.getBookByBookId(event.getBookId());
        bookRepository.delete(book);
    }


}
