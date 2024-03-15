package com.prodemy.project.service;

import com.prodemy.project.model.request.CategoryRequest;
import com.prodemy.project.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List <CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Integer id);
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Integer id, CategoryRequest request);
    void deleteCategory(Integer id);
}
