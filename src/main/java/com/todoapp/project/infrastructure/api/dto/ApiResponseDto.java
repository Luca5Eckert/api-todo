package com.todoapp.project.infrastructure.api.dto;

import java.time.LocalDateTime;

public record ApiResponseDto<T>(int typeResponse, String errorCode, String message, T data, LocalDateTime dataTime) {

    public static ApiResponseDto<Void> error(int typeResponse, String errorCode, String message){
        return new ApiResponseDto<Void>(typeResponse, errorCode, message, null, LocalDateTime.now());
    }

    public static <T> ApiResponseDto<T> success(int typeResponse, String message, T data){
        return new ApiResponseDto<T>(typeResponse, null, message, data, LocalDateTime.now());
    }

    public static ApiResponseDto<Void> success(int typeResponse, String message){
        return new ApiResponseDto<Void>(typeResponse, null, message, null, LocalDateTime.now());
    }
}
