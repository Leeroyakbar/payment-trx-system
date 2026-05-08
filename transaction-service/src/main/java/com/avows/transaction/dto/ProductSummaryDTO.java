package com.avows.transaction.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSummaryDTO {

    private UUID productId;
    private Integer quantity;
    private BigDecimal totalAmount;
}
