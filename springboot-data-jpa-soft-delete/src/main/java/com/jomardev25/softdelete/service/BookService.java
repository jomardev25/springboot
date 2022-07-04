package com.jomardev25.softdelete.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jomardev25.softdelete.entity.Author;
import com.jomardev25.softdelete.entity.Book;
import com.jomardev25.softdelete.entity.BookDetail;
import com.jomardev25.softdelete.http.dto.BookRequest;
import com.jomardev25.softdelete.repository.AuthorRepository;
import com.jomardev25.softdelete.repository.BookDetailRepository;
import com.jomardev25.softdelete.repository.BookRepository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;

    @Autowired
    public BookService(AuthorRepository authorRepository, BookRepository bookRepository,
                       BookDetailRepository bookDetailRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookDetailRepository = bookDetailRepository;
    }

    public ResponseEntity<Object> addBook(BookRequest request) {
        log.info("Save new book: {}", request);

        log.info("Find author by author id");
        Optional<Author> author = authorRepository.findOne(request.getAuthorId());
        if (author.isEmpty()) return ResponseEntity.notFound().build();

        Book book = Book.builder()
                .author(author.get())
                .detail(BookDetail.builder()
                        .page(request.getPage())
                        .weight(request.getWeight())
                        .build())
                .title(request.getTitle())
                .price(request.getPrice())
                .build();
        return ResponseEntity.ok().body(bookRepository.save(book));
    }

    public ResponseEntity<Object> getAllBook() {
        return ResponseEntity.ok().body(bookRepository.findAll());
    }

    public ResponseEntity<Object> getBookDetail(Long bookId) {
        log.info("Find book detail by book id: {}", bookId);
        Optional<BookDetail> bookDetail = bookDetailRepository.findOne(bookId);
        if (bookDetail.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        return ResponseEntity.ok().body(bookDetail.get());
    }

    public ResponseEntity<Object> deleteBook(Long bookId) {
        log.info("Find book detail by book id for delete: {}", bookId);
        try {
            bookDetailRepository.delete(bookId);
            bookRepository.delete(bookId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));
        }
        return ResponseEntity.ok().body(Map.ofEntries(Map.entry("message", "ok")));
    }

    public ResponseEntity<Object> updatePrice(BookRequest request, Long bookId) {
        log.info("Update price: {}", request);
        Optional<Book> book = bookRepository.findOne(bookId);
        if (book.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

        book.get().setPrice(request.getPrice());
        bookRepository.save(book.get());
        return ResponseEntity.ok().body(book.get());
    }

}
