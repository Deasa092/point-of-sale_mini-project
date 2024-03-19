package com.prodemy.project.model.mapper;

import com.prodemy.project.entity.Category;
import com.prodemy.project.entity.Products;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductResponse toResponse(Products products) {
        ProductResponse response = new ProductResponse();
        response.setId(products.getId());
        response.setCategory_id(products.getCategory().getId()); // Mengambil ID objek Category
        response.setTitle(products.getTitle());
        response.setPrice(products.getPrice());
        response.setImage(products.getImage());
        return response;

    }

    public Products toEntity(ProductRequest request) {
        Products product = new Products();
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setImage(request.getImage());

        // Assuming category_id is directly set to Category entity
        Category category = new Category();
        category.setId(request.getCategory_id());
        product.setCategory(category);

        return product;
    }

}
