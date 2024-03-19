package com.prodemy.project.model.mapper;

import com.prodemy.project.entity.Products;
import com.prodemy.project.model.response.ProductDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailResponse toResponse(Products products){
        if (products ==null){
            return null;
        }

        ProductDetailResponse response = new ProductDetailResponse();
        response.setId(products.getId());
        response.setTitle(products.getTitle());
        response.setImage(products.getImage());
        response.setPrice(products.getPrice());
        response.setCategory_id(products.getCategory().getId());
        response.setCategory_name(products.getCategory().getName());

        return response;
    }
}
