package com.prodemy.project.controller;

import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.MessageResponse;
import com.prodemy.project.model.response.ProductDetailResponse;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Configuration
@RestController
@RequestMapping("/pos/api")
public class ProductController {

@Autowired (required = false)
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String title) {

        if (categoryId != null && title != null && sortBy !=null && sortOrder!=null) {
            List<ProductResponse> products = productService.findByCategoryIdAndTitleAndSort(categoryId, title, sortBy, sortOrder);
            return ResponseEntity.ok(products);
        } else if (sortBy != null && sortOrder != null) {
            List<ProductResponse> products = productService.searchAndSort(title, sortBy, sortOrder);
            return ResponseEntity.ok(products);
        } else if (categoryId != null) {
            List<ProductResponse> products = productService.getProductByCategoryId(categoryId);
            return ResponseEntity.ok(products);
        } else if (title != null) {
            List<ProductResponse> products = productService.searchByTitle(title);
            return ResponseEntity.ok(products);
        } else {
            List<ProductResponse> products = productService.getAllProduct();
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping ("listproduct/{id}")
    public ResponseEntity <ProductResponse> getProductById(@PathVariable Integer id){
        try {
            ProductResponse pResponse = productService.getProductById(id);
            return new ResponseEntity<>(pResponse, HttpStatus.OK);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity <ProductDetailResponse> getProductDetail(@PathVariable Integer id){
        try {
            ProductDetailResponse productDetail = productService.getProductDetail(id);
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity <MessageResponse> createProduct (@RequestBody ProductRequest pRequest){
        ProductResponse pResponse = productService.createProduct(pRequest);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<MessageResponse> updateProduct (@PathVariable Integer id, @RequestBody ProductRequest pRequest) {
        try {
            ProductResponse pResponse = productService.updateProduct(id, pRequest);
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus("ok");
            messageResponse.setMessage("sukses");
            return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
//        ProductResponse productResponse = productService.updateProduct(id, pRequest);
//        return new ResponseEntity<>(productResponse, HttpStatus.OK);
//   }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <MessageResponse> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }
}

