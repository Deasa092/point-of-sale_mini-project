package com.prodemy.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id", referencedColumnName = "id")
    private Category category;
    private String title;
    private Integer price;
    private String image;

}
