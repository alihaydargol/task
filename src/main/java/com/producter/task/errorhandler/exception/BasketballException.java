package com.producter.task.errorhandler.exception;

public class BasketballException extends RuntimeException{

    private final ExceptionType exceptionType;
    public BasketballException(ExceptionType type) {
        super(type.name());
        exceptionType = type;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
