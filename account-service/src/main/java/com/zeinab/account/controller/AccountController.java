package com.zeinab.account.controller;


import com.zeinab.account.service.AccountService;
import com.zeinab.core.dto.AccountDTO;
import com.zeinab.core.dto.TransactionDTO;
import com.zeinab.core.model.Account;
import com.zeinab.core.model.Customer;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Bank service endpoints")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/customers/{customerId}/accounts")
    @ApiOperation(value = "Get list of accounts by customer ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get all accounts by customer ID done successfully")
    })
    public ResponseEntity<?> getAccountsByCustomerId(
            @ApiParam(value = "Customer ID", required = true)
            @PathVariable("customerId") long customerId) {
        Optional<Customer> customer = accountService.getCustomerById(customerId);
        if (customer.isPresent()) {
            List<AccountDTO> accountDTOS = accountService.getAccountsByCustomerId(customerId);
            return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    String.format("Customer with ID %s not found.", customerId),
                    HttpStatus.NOT_FOUND
            );
    }

    @GetMapping("/accounts/{accountId}/transactions")
    @ApiOperation(value = "Get list of transactions by account ID")
    @ApiResponses({
            @ApiResponse(code = 404, message = "account not found"),
            @ApiResponse(code = 200, message = "Get all transactions done successfully")
    })
    public ResponseEntity<?> getTransactionByAccountId(
            @ApiParam(value = "Account ID", required = true)
            @PathVariable("accountId") long accountId
    ) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isPresent()) {
            List<TransactionDTO> transactionDTOS = accountService.getTransactionsByAccountId(accountId);
            return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
        } else
            return new ResponseEntity<>(
                    String.format("Account with ID %s not found.", accountId),
                    HttpStatus.NOT_FOUND
            );
    }
}
