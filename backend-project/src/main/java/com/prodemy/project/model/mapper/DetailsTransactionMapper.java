package com.prodemy.project.model.mapper;

import com.prodemy.project.entity.Category;
import com.prodemy.project.entity.Products;
import com.prodemy.project.entity.Transaction;
import com.prodemy.project.entity.TransactionDetail;
import com.prodemy.project.model.request.DetailsTransactionRequest;
import com.prodemy.project.model.response.DetailsTransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailsTransactionMapper {

    public DetailsTransactionResponse toResponse(TransactionDetail transactionDetail) {
        if (transactionDetail == null) {
            return null;
        }
        DetailsTransactionResponse response = new DetailsTransactionResponse();
        response.setId(transactionDetail.getId());
        response.setTransactionId(transactionDetail.getTransaction());
        response.setProductId(transactionDetail.getProduct().getId());
        response.setQuantity(transactionDetail.getQuantity());
        response.setSubtotal(transactionDetail.getSubtotal());
        return response;
    }

    public TransactionDetail toEntity(DetailsTransactionRequest request) {
        if (request == null) {
            return null;
        }
        TransactionDetail transactionDetail = new TransactionDetail();

        Transaction transaction =new Transaction();
        transaction.setId(request.getTransactionId());
        transactionDetail.setTransaction(transaction);

        Products products = new Products();
        products.setId(request.getProductId());
        transactionDetail.setProduct(products);

        transactionDetail.setQuantity(request.getQuantity());
        transactionDetail.setSubtotal(request.getSubtotal());

        return transactionDetail;
    }

}
