package com.zeinab.transcation.service;

import com.zeinab.core.dto.TransactionDTO;
import com.zeinab.core.facade.TransactionInterface;
import com.zeinab.core.model.Transaction;
import com.zeinab.transcation.persistence.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements TransactionInterface {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> getTransactions(long accountId) {
        List<Transaction> transactions = transactionRepository.findAllTransactionsByAccountId(accountId);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        BigDecimal currentBalance = new BigDecimal(0);
        for (Transaction transaction : transactions) {
            if (accountId == transaction.getFrom().getId())
                transaction.setAmount(transaction.getAmount().negate());
            currentBalance = currentBalance.add(transaction.getAmount());
            transactionDTOS.add(new TransactionDTO(transaction, currentBalance));
        }

        return transactionDTOS;
    }
}
