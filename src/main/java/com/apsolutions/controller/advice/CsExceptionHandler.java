package com.apsolutions.controller.advice;

import com.apsolutions.exception.CsException;
import com.apsolutions.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CsExceptionHandler {

    @ExceptionHandler(CsException.class)
    public ResponseEntity<ApiResponse<String>> handlerCsException(CsException ex) {
        ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
