package com.sparta.corporatechallenge1.dto;

import com.sparta.corporatechallenge1.exception.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private String code;
    private String message;


    public static ErrorResponseDto of(ErrorCode code) {
        return new ErrorResponseDto(code.getCode(), code.getMessage());
    }
}
