package com.avows.transaction.enums;

import lombok.Getter;

@Getter
public enum TrxStatus {

    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private final String status;

    TrxStatus(String status) {
        this.status = status;
    }
}
