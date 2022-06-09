package com.example.filehandlerservice.advice;

import com.example.filehandlerservice.data.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.FileNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        String error = "File too large!";
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, error, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {
        String error = "File does not exists!";
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, error, ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
