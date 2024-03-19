package com.prodemy.project.model.response;

import com.prodemy.project.entity.Transaction;
import lombok.Data;

@Data
public class DetailsTransactionResponse {
    private Integer id;
    private Transaction transactionId;
    private Integer productId;
    private Integer quantity;
    private Long subtotal;

}
