package com.todoapp.project.infrastructure.exceptions;

import com.todoapp.project.infrastructure.api.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponseDto<Void>> handleRuntimeException(RuntimeException runtimeException){
        String message = runtimeException.getMessage();
        return ResponseEntity.badRequest().body(ApiResponseDto.error(400, "RuntimeException", message));
    }
}
