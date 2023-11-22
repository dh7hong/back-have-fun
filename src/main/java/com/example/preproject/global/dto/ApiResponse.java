package com.example.preproject.global.dto;

import com.example.preproject.dto.LoginRequestDto;
import com.example.preproject.dto.LoginResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // 로그인 성공시 사용
    LoginResponseDto successData = new LoginResponseDto("사용자이름", "JWT토큰");
    ApiResponse<LoginResponseDto> response = ApiResponse.successData(successData);

    //에러
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ERROR_STAUTS, message, null);
    }
}