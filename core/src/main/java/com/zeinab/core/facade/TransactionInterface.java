package com.zeinab.core.facade;

import com.zeinab.core.dto.TransactionDTO;

import java.util.List;

public interface TransactionInterface {
    List<TransactionDTO> getTransactions(long accountId);
}
