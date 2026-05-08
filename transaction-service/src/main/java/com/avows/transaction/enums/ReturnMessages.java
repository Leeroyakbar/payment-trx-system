package com.avows.transaction.enums;

import lombok.Getter;

@Getter
public enum ReturnMessages {
    INSUFFICIENT_STOCK("Insufficient stock"),
    PRODUCT_NOT_FOUND("Product not found"),
    STOCK_REDUCED_SUCCESS("Stock reduced successfully"),
    SUCCESS_CREATE_DATA("Success create data"),
    SUCCESS_FETCH_DATA("Success fetch data"),
    SUCCESS_UPDATE_DATA("Success update data");

    private final String message;

    ReturnMessages(String message) {
        this.message = message;
    }
}
