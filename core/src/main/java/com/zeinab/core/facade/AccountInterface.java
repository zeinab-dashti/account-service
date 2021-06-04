package com.zeinab.core.facade;

import com.zeinab.core.dto.AccountDTO;
import com.zeinab.core.dto.TransactionDTO;
import com.zeinab.core.model.Account;
import com.zeinab.core.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AccountInterface {

    Optional<Customer> getCustomerById(long customerId);

    Optional<Account> getAccountById(long accountId);

    List<AccountDTO> getAccountsByCustomerId(long customerId);

    List<TransactionDTO> getTransactionsByAccountId(long accountId);
}
