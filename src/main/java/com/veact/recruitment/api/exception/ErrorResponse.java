package com.veact.recruitment.api.exception;

public class ErrorResponse {

    public String error_code;
    public String message;

    public void generateErrorFromException(BusinessException ex) {
        this.error_code = ex.getCode();
        this.message = ex.getMessage();
    }
}
