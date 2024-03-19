package com.prodemy.project.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    private LocalDateTime transaction_date;
    private Long total_amount;
    private Long total_pay;

}
