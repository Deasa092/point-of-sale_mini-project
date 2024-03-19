package com.prodemy.project.service;

import com.prodemy.project.entity.Transaction;
import com.prodemy.project.model.request.DetailsTransactionRequest;
import com.prodemy.project.model.response.DetailsTransactionResponse;
import com.prodemy.project.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List <TransactionResponse> getAllTransaction();
    TransactionResponse getTransactionById(Integer id);
    DetailsTransactionResponse getTransactionDetailById (Integer id);
    DetailsTransactionResponse addDetailTransaction(DetailsTransactionRequest request);
}
