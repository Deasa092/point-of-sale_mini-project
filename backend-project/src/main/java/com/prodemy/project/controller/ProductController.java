package com.prodemy.project.controller;

import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.ProductRequest;
import com.prodemy.project.model.response.MessageResponse;
import com.prodemy.project.model.response.ProductResponse;
import com.prodemy.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping ("/{id}")
    public ResponseEntity <ProductResponse> getProductById(@PathVariable Integer id){
        try {
            ProductResponse pResponse = productService.getProductById(id);
            return new ResponseEntity<>(pResponse, HttpStatus.OK);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity <MessageResponse> createProduct (@RequestBody ProductRequest pRequest){
        ProductResponse pResponse = productService.createProduct(pRequest);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Product Berhasil Ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }

    @PutMapping ("/{id}")
    public ResponseEntity <ProductResponse> updateProduct (@PathVariable Integer id, @RequestBody ProductRequest pRequest){
//        try {
//            ProductResponse pResponse = productService.updateProduct(id, pRequest);
//            MessageResponse messageResponse = new MessageResponse();
//            messageResponse.setMessage("Product Berhasil DiUpdate");
//            return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
//        }
//        catch (ResourceNotFoundException ex){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        ProductResponse productResponse = productService.updateProduct(id, pRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <MessageResponse> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Product Berhasil Dihapus");
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }
}

