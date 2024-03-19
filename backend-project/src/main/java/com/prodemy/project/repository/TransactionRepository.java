package com.prodemy.project.repository;

import com.prodemy.project.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository <Transaction, Integer> {
}
