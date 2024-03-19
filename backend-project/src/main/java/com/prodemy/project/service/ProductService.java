package com.prodemy.project.service;

import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductDetailResponse;
import com.prodemy.project.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List <ProductResponse> getAllProduct();
    ProductResponse getProductById(Integer id);
    ProductDetailResponse getProductDetail(Integer id);
    List <ProductResponse> getProductByCategoryId (Integer categoryId);
    List <ProductResponse> searchByTitle (String title);
    ProductResponse createProduct (ProductRequest request);
    ProductResponse updateProduct (Integer id, ProductRequest request);
    void deleteProduct (Integer id);
    List <ProductResponse> searchAndSort (String title, String sortBy, String sortOrder);
    List<ProductResponse> findByCategoryIdAndTitleAndSort(Integer categoryId, String title, String sortBy, String sortOrder);

}
