package com.zeinab.account.service;

import com.zeinab.account.client.TransactionClient;
import com.zeinab.account.persistence.AccountRepository;
import com.zeinab.account.persistence.CustomerRepository;
import com.zeinab.core.dto.AccountDTO;
import com.zeinab.core.dto.TransactionDTO;
import com.zeinab.core.facade.AccountInterface;
import com.zeinab.core.model.Account;
import com.zeinab.core.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements AccountInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionClient transactionClient;

    @Override
    public Optional<Customer> getCustomerById(long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Account> getAccountById(long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(long customerId) {
        List<Account> accounts = accountRepository.findAllAccountsByCustomerId(customerId);
        return accounts.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(long accountId) {
        return Arrays.asList(transactionClient.getTransactionsByAccountId(accountId));
    }
}
