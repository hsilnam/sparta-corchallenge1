package com.sparta.corporatechallenge1.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


public class ReviewCreateDto {
    @Getter
    @Setter
    public static class Request {
        private Long userId;
        Long productId;
        MultipartFile image;
        private int score;
        private String content;

    }
}
