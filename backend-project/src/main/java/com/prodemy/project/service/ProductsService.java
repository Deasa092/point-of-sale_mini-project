package com.prodemy.project.service;

import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductDetailResponse;
import com.prodemy.project.model.response.ProductResponse;

import java.util.List;

public interface ProductsService {
    List<ProductResponse> getAllProducts();

    ProductResponse createNewProduct (ProductRequest request);
    ProductResponse updateProductById (Integer id, ProductRequest request);
    void deleteProductById (Integer id);
    ProductDetailResponse getProductDetailById(Integer id);
    List <ProductResponse> getProductByCategoryId (Integer categoryId);
    List <ProductResponse> getProductBySearchTitle (String title);
    List <ProductResponse> getProductBySortAndSearch (String title, String sortBy, String sortOrder);
    List <ProductResponse> getProductByCategorySortAndSearch (Integer categoryId, String title, String sortBy, String sortOrder);
}
