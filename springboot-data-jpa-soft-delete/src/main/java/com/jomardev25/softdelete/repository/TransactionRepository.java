package com.jomardev25.softdelete.repository;

import org.springframework.stereotype.Repository;

import com.jomardev25.softdelete.entity.Transaction;
import com.jomardev25.softdelete.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface TransactionRepository extends SoftDeletesRepository<Transaction, Long> {
}
