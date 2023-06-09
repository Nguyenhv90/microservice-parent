package com.hvn.bookservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreatedEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
