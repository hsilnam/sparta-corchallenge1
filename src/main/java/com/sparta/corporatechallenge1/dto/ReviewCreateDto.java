package com.sparta.corporatechallenge1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


public class ReviewCreateDto {
    @Getter
    @Setter
    public static class Request {
        @NotNull(message = "유저 ID는 필수입니다.")
        private Long userId;

        @NotNull(message = "상품 ID는 필수입니다.")
        private Long productId;

        private MultipartFile image;

        @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
        private int score;

        private String content;

    }
}
