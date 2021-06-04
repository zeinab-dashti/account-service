package com.zeinab.transcation.persistence;

import com.zeinab.core.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t " +
            "FROM Transaction t " +
            "where t.from.id=:accountId OR t.to.id=:accountId " +
            "ORDER BY t.datetime")
    List<Transaction> findAllTransactionsByAccountId(@Param("accountId") Long accountId);
}
