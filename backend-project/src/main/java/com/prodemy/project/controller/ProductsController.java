package com.prodemy.project.controller;

import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.ProductRequest;
//import com.prodemy.project.model.response.CategoryResponse;
import com.prodemy.project.model.response.MessageResponse;
import com.prodemy.project.model.response.ProductDetailResponse;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/pos/api")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
//    @GetMapping("/list")
//    public List <ProductResponse> getAll(){
//        return productsService.getAllProducts();
//    }

    @GetMapping("/listproduct")
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String title) {

        if (categoryId != null && title != null && sortBy !=null && sortOrder!=null) {
            List<ProductResponse> products = productsService.getProductByCategorySortAndSearch(categoryId, title, sortBy, sortOrder);
            return ResponseEntity.ok(products);
        } else if (sortBy != null && sortOrder != null) {
            List<ProductResponse> products = productsService.getProductBySortAndSearch(title, sortBy, sortOrder);
            return ResponseEntity.ok(products);
        } else if (categoryId != null) {
            List<ProductResponse> products = productsService.getProductByCategoryId(categoryId);
            return ResponseEntity.ok(products);
        } else if (title != null) {
            List<ProductResponse> products = productsService.getProductBySearchTitle(title);
            return ResponseEntity.ok(products);
        } else {
            List<ProductResponse> products = productsService.getAllProducts();
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity <ProductDetailResponse> getProductDetail(@PathVariable Integer id){
        try {
            ProductDetailResponse productDetail = productsService.getProductDetailById(id);
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PostMapping("/add")
    public ResponseEntity <MessageResponse> createProduct (@RequestBody ProductRequest pRequest){
        ProductResponse pResponse = productsService.createNewProduct(pRequest);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<MessageResponse> updateProduct (@PathVariable Integer id, @RequestBody ProductRequest pRequest) {
        try {
            ProductResponse pResponse = productsService.updateProductById(id, pRequest);
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus("ok");
            messageResponse.setMessage("sukses");
            return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity <MessageResponse> deleteProduct(@PathVariable Integer id){
        productsService.deleteProductById(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }
}

