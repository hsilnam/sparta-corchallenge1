package com.sparta.corporatechallenge1.exception;

public class ReviewException extends CustomException {
    public ReviewException(ErrorCode errorCode) {
        super(errorCode);
    }
}
