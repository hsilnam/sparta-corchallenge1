package com.sparta.corporatechallenge1.dto;

import lombok.*;

import java.time.LocalDateTime;


public class ReviewDto {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long userId;
        private int score;
        private String content;
        private String imageUrl;
        private LocalDateTime createdAt;
    }
}
