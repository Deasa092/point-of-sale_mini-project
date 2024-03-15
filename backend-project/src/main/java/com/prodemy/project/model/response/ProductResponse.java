package com.prodemy.project.model.response;

import com.prodemy.project.entity.Category;
import lombok.Data;

@Data
public class ProductResponse {
    private Integer id;
    private Integer category_id;
    private String title;
    private Integer price;
    private String image;

}

