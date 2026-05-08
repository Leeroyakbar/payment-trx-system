package com.avows.transaction.controller;

import com.avows.client.dto.ApiResponseDTO;
import com.avows.transaction.dto.ProductSummaryDTO;
import com.avows.transaction.dto.SummaryTransactionResponse;
import com.avows.transaction.dto.TransactionRequest;
import com.avows.transaction.dto.TransactionResponse;
import com.avows.transaction.enums.ReturnMessages;
import com.avows.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(request);
        return ResponseEntity.ok(ApiResponseDTO.ok(response, ReturnMessages.SUCCESS_CREATE_DATA.getMessage()));
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponseDTO<SummaryTransactionResponse>> getSummaryTransaction() {
        SummaryTransactionResponse response = transactionService.getSummaryTransaction();
        return ResponseEntity.ok(ApiResponseDTO.ok(response, ReturnMessages.SUCCESS_FETCH_DATA.getMessage()));
    }
}
