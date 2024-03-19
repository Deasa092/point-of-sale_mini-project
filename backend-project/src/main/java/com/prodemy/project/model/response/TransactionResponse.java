package com.prodemy.project.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Integer id;
    private LocalDateTime transaction_date;
    private Long total_amount;
    private Long total_pay;
}
