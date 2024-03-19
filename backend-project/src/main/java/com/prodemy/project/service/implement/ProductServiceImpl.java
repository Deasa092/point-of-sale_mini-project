package com.prodemy.project.service.implement;

import com.prodemy.project.entity.Category;
import com.prodemy.project.entity.Products;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.mapper.ProductDetailMapper;
import com.prodemy.project.model.mapper.ProductMapper;
import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.ProductDetailResponse;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.repository.ProductsRepository;
import com.prodemy.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public abstract class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailMapper productDetailMapper;

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
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " is Not Found"));

        return productMapper.toResponse(products);
    }

    @Override
    public ProductDetailResponse getProductDetail(Integer id) {
        Optional<Products> opsiProduct = productsRepository.findById(id);
        if (opsiProduct.isPresent()){
            Products products = opsiProduct.get();
            return productDetailMapper.toResponse(products);
        } else {
            throw new ResourceNotFoundException("Produk dengan id "+id+" tidak ditemukan");
        }
    }


    @Override
    public List<ProductResponse> getProductByCategoryId(Integer categoryId) {
        List<Products> products = productsRepository.findByCategoryId(categoryId);
        return mapProductsToResponse(products);
    }


    @Override
    public List<ProductResponse> searchByTitle(String title) {
        List <Products> products = productsRepository.findByTitle(title);
        return mapProductsToResponse(products);
    }

    private List<ProductResponse> mapProductsToResponse(List<Products> products) {
        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
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

    @Override
    public List<ProductResponse> searchAndSort(String title, String sortBy, String sortOrder) {
        List<Products> products = null;
        if (sortBy != null && sortOrder != null) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                products = productsRepository.findByTitleAndSortAscending(title, sortBy);
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                products = productsRepository.findByTitleAndSortDescending(title, sortBy);
            }
        } else {
            products = productsRepository.findByTitle(title);
        }
        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByCategoryIdAndTitleAndSort(Integer categoryId, String title, String sortBy, String sortOrder) {
        List<Products> products;
        if ("price".equalsIgnoreCase(sortBy)) {
            if ("desc".equalsIgnoreCase(sortOrder)) {
                products = productsRepository.findByCategoryIdAndTitleAndSortByPriceDescending(categoryId, title);
            } else {
                products = productsRepository.findByCategoryIdAndTitleAndSortByPriceAscending(categoryId, title);
            }
        } else if ("name".equalsIgnoreCase(sortBy)) {
            if ("desc".equalsIgnoreCase(sortOrder)) {
                products = productsRepository.findByCategoryIdAndTitleAndSortByNameDescending(categoryId, title);
            } else {
                products = productsRepository.findByCategoryIdAndTitleAndSortByNameAscending(categoryId, title);
            }
        } else {
            // Default sorting by price ascending if sortBy parameter is not provided or invalid
            products = productsRepository.findByCategoryIdAndTitleAndSortByPriceAscending(categoryId, title);
        }
        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
}
