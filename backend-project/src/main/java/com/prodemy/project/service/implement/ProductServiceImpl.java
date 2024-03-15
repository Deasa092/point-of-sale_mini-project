package com.prodemy.project.service.implement;

import com.prodemy.project.entity.Category;
import com.prodemy.project.entity.Products;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.mapper.ProductMapper;
import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.repository.ProductsRepository;
import com.prodemy.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductResponse> getAllProduct() {
        List<Products> products = productsRepository.findAll();

        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Products products =productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " is Not Found"));

        return productMapper.toResponse(products);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Products products = productMapper.toEntity(request);
        products = productsRepository.save(products);

        return productMapper.toResponse(products);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        Products existingProduct = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " is Not Found"));

        existingProduct.setTitle(request.getTitle());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setImage(request.getImage());

        if(request.getCategory_id() !=null){
            Category category = new Category();
            category.setId(request.getCategory_id());
            existingProduct.setCategory(category);
        }
         existingProduct = productsRepository.save(existingProduct);
        return productMapper.toResponse(existingProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productsRepository.delete(product);
    }
}
