package com.prodemy.project.model.mapper;

import com.prodemy.project.entity.Category;
import com.prodemy.project.model.request.CategoryRequest;
import com.prodemy.project.model.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse convertToResponse(Category category){
        CategoryResponse cResponse = new CategoryResponse();
        cResponse.setId(category.getId());
        cResponse.setName(category.getName());

        return cResponse;
    }

    public Category convertToEntity(CategoryRequest cRequest){
        Category category = new Category();
        category.setName(cRequest.getName());

        return category;
    }
}
