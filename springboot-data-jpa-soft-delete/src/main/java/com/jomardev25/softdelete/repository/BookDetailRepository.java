package com.jomardev25.softdelete.repository;

import org.springframework.stereotype.Repository;

import com.jomardev25.softdelete.entity.BookDetail;
import com.jomardev25.softdelete.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface BookDetailRepository extends SoftDeletesRepository<BookDetail, Long> {
}
