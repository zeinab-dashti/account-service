package com.zeinab.transcation.controller;

import com.zeinab.core.dto.TransactionDTO;
import com.zeinab.transcation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccount(
            @PathVariable("accountId") long accountId) {
        return ResponseEntity.ok().body(transactionService.getTransactions(accountId));
    }
}
