package com.prodemy.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "transaction_details")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    private Products product;

    private Integer quantity;
    private Long subtotal;
}
