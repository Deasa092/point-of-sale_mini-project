package com.prodemy.project.model.request;

import com.prodemy.project.entity.Category;
import lombok.Data;

@Data
public class ProductRequest {
    private Integer category_id;
    private String title;
    private Integer price;
    private String image;
}
