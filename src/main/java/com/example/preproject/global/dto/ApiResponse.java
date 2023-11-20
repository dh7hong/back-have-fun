package com.example.preproject.global.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STAUTS = "error";

    private String status;
    private String message;
    private T data;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //성공
    public static <T> ApiResponse<T> successData(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, null, data);
    }

    public static <T> ApiResponse<T> successMessage(String message) {
        return new ApiResponse<>(SUCCESS_STATUS, message, null);
    }

    //에러
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ERROR_STAUTS, message, null);
    }
}