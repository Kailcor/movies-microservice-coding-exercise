package com.veact.recruitment.api.exception;

public class NotFoundBusinessException extends BusinessException{


    public NotFoundBusinessException(String message) {
        super("010", message);
    }

    public NotFoundBusinessException(String code, String message) {
        super(code, message);
    }
}
