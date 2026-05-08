package com.avows.transaction.repository;

import com.avows.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(value = "SELECT product_id, SUM(quantity) as total_qty, SUM(trx_amount) as total_amount " +
            "FROM trx_transactions GROUP BY product_id", nativeQuery = true)
    List<Object[]> getTransactionSummary();
}
