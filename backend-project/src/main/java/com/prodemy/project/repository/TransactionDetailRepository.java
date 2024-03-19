package com.prodemy.project.repository;

import com.prodemy.project.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository <TransactionDetail, Integer> {

}
