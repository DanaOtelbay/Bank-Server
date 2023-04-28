package com.example.bankserverproject.repository;

import com.example.bankserverproject.domain.model.BalanceTransaction;
import com.example.bankserverproject.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceTransactionRepository extends JpaRepository<BalanceTransaction, Long> {
}
