package com.prodemy.project.service.implement;

import com.prodemy.project.entity.Transaction;
import com.prodemy.project.entity.TransactionDetail;
import com.prodemy.project.exception.ResourceNotFoundException;
import com.prodemy.project.model.mapper.DetailsTransactionMapper;
import com.prodemy.project.model.mapper.TransactionMapper;
import com.prodemy.project.model.request.DetailsTransactionRequest;
import com.prodemy.project.model.response.DetailsTransactionResponse;
import com.prodemy.project.model.response.TransactionResponse;
import com.prodemy.project.repository.TransactionDetailRepository;
import com.prodemy.project.repository.TransactionRepository;
import com.prodemy.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private DetailsTransactionMapper detailsTransactionMapper;

    @Override
    public List<TransactionResponse> getAllTransaction() {
        List <Transaction> transactions = transactionRepository.findAll();

        return transactions.stream()
                .map(transactionMapper::toResponse) // Menggunakan constructor CategoryResponse
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Transaksi dengan id" + id + " tidak ditemukan"));
        return transactionMapper.toResponse(transaction);
    }

    @Override
    public DetailsTransactionResponse getTransactionDetailById(Integer id) {
        TransactionDetail transactionDetail = transactionDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Transaksi dengan id" + id + " tidak ditemukan"));
        return detailsTransactionMapper.toResponse(transactionDetail);
    }

    @Override
    public DetailsTransactionResponse addDetailTransaction(DetailsTransactionRequest request) {
        TransactionDetail transactionDetail = detailsTransactionMapper.toEntity(request);
        transactionDetail = transactionDetailRepository.save(transactionDetail);

        return detailsTransactionMapper.toResponse(transactionDetail);
    }
}
