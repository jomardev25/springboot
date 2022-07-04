package com.jomardev25.softdelete.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jomardev25.softdelete.http.dto.BookRequest;
import com.jomardev25.softdelete.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<Object> getBookDetail(@PathVariable(value = "id") Long bookId) {
        return bookService.getBookDetail(bookId);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> updatePrice(@PathVariable(value = "id") Long bookId,
                                              @RequestBody BookRequest request) {
        return bookService.updatePrice(request, bookId);
    }

}
