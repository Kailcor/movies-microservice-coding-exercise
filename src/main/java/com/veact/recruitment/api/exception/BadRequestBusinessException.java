package com.veact.recruitment.api.exception;

public class BadRequestBusinessException extends BusinessException{


    public BadRequestBusinessException(String message) {
        super("010", message);
    }

    public BadRequestBusinessException(String code, String message) {
        super(code, message);
    }
}
