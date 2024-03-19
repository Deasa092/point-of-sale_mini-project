package com.prodemy.project.controller;

import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.CategoryRequest;
import com.prodemy.project.model.response.CategoryResponse;
import com.prodemy.project.model.response.MessageResponse;
import com.prodemy.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pos/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping ("/listcategory")
    public List <CategoryResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/listcategory/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id){
        try{
            CategoryResponse cResponse = categoryService.getCategoryById(id);
            return new ResponseEntity<>(cResponse, HttpStatus.OK);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity <MessageResponse> createCategory(@RequestBody CategoryRequest cRequest){
        CategoryResponse cResponse = categoryService.createCategory(cRequest);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity <MessageResponse> updateCategory (@PathVariable Integer id, @RequestBody CategoryRequest cRequest){
        try{
            CategoryResponse cResponse = categoryService.updateCategory(id, cRequest);
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setStatus("ok");
            messageResponse.setMessage("sukses");
            return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Data Berhasil Dihapus");
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }

}
