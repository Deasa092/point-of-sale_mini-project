package com.prodemy.project.model.response;

import lombok.Data;

@Data
public class ProductDetailResponse {
    private Integer id;
    private String title;
    private String image;
    private Integer price;
    private Integer category_id;
    private String category_name;
}
