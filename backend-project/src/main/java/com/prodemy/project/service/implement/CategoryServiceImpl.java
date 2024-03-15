package com.prodemy.project.service.implement;

import com.prodemy.project.entity.Category;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.mapper.CategoryMapper;
import com.prodemy.project.model.request.CategoryRequest;
import com.prodemy.project.model.response.CategoryResponse;
import com.prodemy.project.repository.CategoryRepository;
import com.prodemy.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List <CategoryResponse> getAllCategory(){
        List <Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::convertToResponse) // Menggunakan constructor CategoryResponse
                .collect(Collectors.toList());

    }
    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        return categoryMapper.convertToResponse(category);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.convertToEntity(request);
        category = categoryRepository.save(category);

        return categoryMapper.convertToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        existingCategory.setName(request.getName());
        existingCategory= categoryRepository.save(existingCategory);

        return categoryMapper.convertToResponse(existingCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);

    }

}
