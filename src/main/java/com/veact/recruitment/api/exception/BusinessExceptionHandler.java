package com.veact.recruitment.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> getError(BusinessException ex) {
        ErrorResponse error = new ErrorResponse();
        error.generateErrorFromException(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NotFoundBusinessException.class)
    public ResponseEntity<ErrorResponse> getError(NotFoundBusinessException ex) {
        ErrorResponse error = new ErrorResponse();
        error.generateErrorFromException(ex);
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
