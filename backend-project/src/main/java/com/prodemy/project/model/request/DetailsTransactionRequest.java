package com.prodemy.project.model.request;

import lombok.Data;

@Data
public class DetailsTransactionRequest {
    private Integer transactionId;
    private Integer productId;
    private Integer quantity;
    private Long subtotal;

}
