package com.apsolutions.controller.advice;

import com.apsolutions.exception.CsException;
import com.apsolutions.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CsException.class)
    public ResponseEntity<ApiResponse<String>> handlerCsException(CsException ex) {
        ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handlerAuthenticationException(Exception ex) {
        ApiResponse<String> response;

        if (ex instanceof BadCredentialsException) {
            response = new ApiResponse<>(false, "Bad credentials");
        } else {
            response = new ApiResponse<>(false, "Don't have permission");
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
