package com.jomardev25.softdelete.http.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jomardev25.softdelete.http.dto.AuthorRequest;
import com.jomardev25.softdelete.service.AuthorService;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorRequest request) {
        return authorService.save(request);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllAuthor() {
        return authorService.getAll();
    }

}
