package com.jomardev25.softdelete.repository;

import org.springframework.stereotype.Repository;

import com.jomardev25.softdelete.entity.TransactionDetail;
import com.jomardev25.softdelete.repository.softdeletes.SoftDeletesRepository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends SoftDeletesRepository<TransactionDetail, Long> {

    List<TransactionDetail> findAllByTransactionId(Long transactionId);

}
