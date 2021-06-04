package com.zeinab.core.dto;

import com.zeinab.core.model.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class TransactionDTO {

    private long datetime;
    private long fromAccountId;
    private long toAccountId;
    private BigDecimal amount;
    private BigDecimal balanceAfter;

    public TransactionDTO(Transaction transaction, BigDecimal balanceAfter) {
        this.datetime = transaction.getDatetime();
        this.fromAccountId = transaction.getFrom().getId();
        this.toAccountId = transaction.getTo().getId();
        this.amount = transaction.getAmount();
        this.balanceAfter = balanceAfter;
    }
}
