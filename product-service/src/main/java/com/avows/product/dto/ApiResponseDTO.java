package com.avows.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDTO<T> {
    private Boolean success;
    private String errors;
    private String errorCodes;
    private T data;
    private String messages;
    private String messageCodes;

    public static <T> ApiResponseDTO<T> ok(T data, String message) {
        return ApiResponseDTO.<T>builder()
                .success(true)
                .data(data)
                .messages(message)
                .messageCodes("200")
                .build();
    }
}
