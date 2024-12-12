package com.sparta.corporatechallenge1.exception;

public class ProductException extends CustomException {
    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }
}
