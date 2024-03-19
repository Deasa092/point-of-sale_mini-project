package com.prodemy.project.controller;

import com.prodemy.project.entity.Transaction;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.request.DetailsTransactionRequest;
import com.prodemy.project.model.response.DetailsTransactionResponse;
import com.prodemy.project.model.response.MessageResponse;
import com.prodemy.project.model.response.TransactionResponse;
import com.prodemy.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/pos/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping ("/listtransaction")
    public List <TransactionResponse> getAllTransactions(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/listtransaction/{id}")
    public ResponseEntity <TransactionResponse> getTransactionById(@PathVariable Integer id){
        try{
            TransactionResponse transactionResponse = transactionService.getTransactionById(id);
            return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/transaction/detail/{id}")
    public ResponseEntity <DetailsTransactionResponse> getDetailTransactionById(@PathVariable Integer id){
        try {
            DetailsTransactionResponse detailsTransactionResponse = transactionService.getTransactionDetailById(id);
            return new ResponseEntity<>(detailsTransactionResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @PostMapping ("/addtransaction")
    public ResponseEntity <MessageResponse> addDetaiTransaction (@RequestBody DetailsTransactionRequest request){
        DetailsTransactionResponse response = transactionService.addDetailTransaction(request);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus("ok");
        messageResponse.setMessage("sukses");
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);

    }
}
