package com.hvn.bookservice.query.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}
