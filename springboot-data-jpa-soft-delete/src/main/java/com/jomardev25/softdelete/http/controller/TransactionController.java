package com.jomardev25.softdelete.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jomardev25.softdelete.http.dto.TransactionRequest;
import com.jomardev25.softdelete.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTransactionDetail(@PathVariable(value = "id") Long transactionId) {
        return transactionService.getTransactionDetails(transactionId);
    }

}
