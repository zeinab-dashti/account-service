package com.zeinab.account.persistence;

import com.zeinab.core.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllAccountsByCustomerId(Long customerId);
}

