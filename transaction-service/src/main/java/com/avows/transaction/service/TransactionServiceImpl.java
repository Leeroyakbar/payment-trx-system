package com.avows.transaction.service;

import com.avows.transaction.client.ProductClient;
import com.avows.transaction.dto.*;
import com.avows.transaction.entity.Transaction;
import com.avows.transaction.enums.ReturnMessages;
import com.avows.transaction.enums.TrxStatus;
import com.avows.transaction.exception.BadRequestException;
import com.avows.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductClient productClient;


    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {
        ProductDTO product = productClient.getProductById(request.getProductId());

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException(ReturnMessages.INSUFFICIENT_STOCK.getMessage() + " for product: " + product.getProductName());
        }

        productClient.reduceStock(request.getProductId(), request.getQuantity());

        Transaction trx = Transaction.builder()
                .trxId(UUID.randomUUID())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .trxAmount(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())))
                .trxDate(LocalDateTime.now())
                .trxStatus(TrxStatus.SUCCESS.getStatus())
                .build();

        transactionRepository.save(trx);

        return TransactionResponse.builder()
                .trxId(trx.getTrxId())
                .productId(trx.getProductId())
                .quantity(trx.getQuantity())
                .trxAmount(trx.getTrxAmount())
                .trxDate(trx.getTrxDate())
                .trxStatus(trx.getTrxStatus())
                .build();
    }

    @Override
    public SummaryTransactionResponse getSummaryTransaction() {
        List<Object[]> transactionSummary = transactionRepository.getTransactionSummary();

        List<ProductSummaryDTO> details = transactionSummary.stream()
                .map(row -> ProductSummaryDTO.builder()
                        .productId((UUID) row[0])
                        .quantity(row[1] != null ? ((Number) row[1]).intValue() : 0)
                        .totalAmount(row[2] != null ? new BigDecimal(row[2].toString()) : BigDecimal.ZERO)
                        .build())
                .toList();

        BigDecimal grandTotal = details.stream()
                .map(ProductSummaryDTO::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return SummaryTransactionResponse.builder()
                .summary(details)
                .grandRevenue(grandTotal)
                .build();
    }
}
