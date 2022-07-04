package com.jomardev25.softdelete.repository;

import org.springframework.stereotype.Repository;

import com.jomardev25.softdelete.entity.Book;
import com.jomardev25.softdelete.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface BookRepository extends SoftDeletesRepository<Book, Long> {
}
