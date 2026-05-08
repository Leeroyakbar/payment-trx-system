package com.avows.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TRX_TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "TRX_ID")
    private UUID trxId;

    @Column(name = "PRODUCT_ID")
    private UUID productId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TRX_AMOUNT")
    private BigDecimal trxAmount;

    @Column(name = "TRX_DATE")
    private LocalDateTime trxDate;

    @Column(name = "TRX_STATUS")
    private String trxStatus;
}
