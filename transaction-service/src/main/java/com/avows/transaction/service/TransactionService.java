package com.avows.transaction.service;

import com.avows.transaction.dto.ProductSummaryDTO;
import com.avows.transaction.dto.SummaryTransactionResponse;
import com.avows.transaction.dto.TransactionRequest;
import com.avows.transaction.dto.TransactionResponse;

public interface TransactionService {

    TransactionResponse createTransaction(TransactionRequest request);

    SummaryTransactionResponse getSummaryTransaction();
}
