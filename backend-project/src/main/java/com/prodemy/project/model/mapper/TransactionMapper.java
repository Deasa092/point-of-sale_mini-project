package com.prodemy.project.model.mapper;

import com.prodemy.project.entity.Transaction;
import com.prodemy.project.model.request.TransactionRequest;
import com.prodemy.project.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponse toResponse(Transaction transaction){
        TransactionResponse tResponse = new TransactionResponse();
        tResponse.setId(transaction.getId());
        tResponse.setTransaction_date(transaction.getTransactionDate());
        tResponse.setTotal_amount(transaction.getTotalAmount());
        tResponse.setTotal_pay(transaction.getTotalPay());

        return tResponse;
    }

    public Transaction toEntity (TransactionRequest tRequest){
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(tRequest.getTransaction_date());
        transaction.setTotalAmount(tRequest.getTotal_amount());
        transaction.setTotalPay(tRequest.getTotal_pay());

        return transaction;
    }
}
