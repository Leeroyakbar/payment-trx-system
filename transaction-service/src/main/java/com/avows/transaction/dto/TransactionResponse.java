package com.avows.transaction.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionResponse {
    private UUID trxId;
    private UUID productId;
    private Integer quantity;
    private BigDecimal trxAmount;
    private LocalDateTime trxDate;
    private String trxStatus;
}
