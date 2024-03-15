package com.prodemy.project.service;

import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List <ProductResponse> getAllProduct();
    ProductResponse getProductById(Integer id);
    ProductResponse createProduct (ProductRequest request);
    ProductResponse updateProduct (Integer id, ProductRequest request);
    void deleteProduct (Integer id);
}
