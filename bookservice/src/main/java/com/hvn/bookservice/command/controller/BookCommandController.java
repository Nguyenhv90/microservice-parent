package com.hvn.bookservice.command.controller;

import com.hvn.bookservice.command.command.CreateBookCommand;
import com.hvn.bookservice.command.command.DeleteBookCommand;
import com.hvn.bookservice.command.command.UpdateBookCommand;
import com.hvn.bookservice.command.model.BookRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookCommandController {
    public final CommandGateway commandGateway;

    @PostMapping("/addBook")
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand bookCommand = new CreateBookCommand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);
        commandGateway.sendAndWait(bookCommand);
        return "added book";
    }

    @PostMapping("/updateBook")
    public String updateBook(@RequestBody BookRequestModel model) {
        UpdateBookCommand bookCommand = new UpdateBookCommand(model.getBookId(), model.getName(), model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(bookCommand);
        return "updated book";
    }

    @DeleteMapping ("/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand bookCommand = new DeleteBookCommand(bookId, "", "", false);
        commandGateway.sendAndWait(bookCommand);
        return "deleted book";
    }
}
