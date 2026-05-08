package com.avows.client.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> {
    private Boolean success;
    private String messages;
    private String messageCodes;
    private String errors;
    private String errorCodes;
    private T data;


    public static <T> ApiResponseDTO<T> ok(T data, String message) {
        return ApiResponseDTO.<T>builder()
                .success(true)
                .data(data)
                .messages(message)
                .messageCodes("200")
                .build();
    }
}
