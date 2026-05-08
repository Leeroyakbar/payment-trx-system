package com.avows.product.enums;

import lombok.Getter;

@Getter
public enum ReturnMessage {

    INSUFFICIENT_STOCK("Insufficient stock"),
    PRODUCT_NOT_FOUND("Product not found"),
    STOCK_REDUCED_SUCCESS("Stock reduced successfully"),
    SUCCESS_FETCH_DATA("Success fetch data"),
    SUCCESS_UPDATE_DATA("Success update data");

    private final String message;

    ReturnMessage(String message) {
        this.message = message;
    }
}
