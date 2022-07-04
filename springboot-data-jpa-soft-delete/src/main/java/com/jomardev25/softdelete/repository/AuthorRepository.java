package com.jomardev25.softdelete.repository;

import org.springframework.stereotype.Repository;

import com.jomardev25.softdelete.entity.Author;
import com.jomardev25.softdelete.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface AuthorRepository extends SoftDeletesRepository<Author, Long> {
}
