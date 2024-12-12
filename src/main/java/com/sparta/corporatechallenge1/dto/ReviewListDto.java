package com.sparta.corporatechallenge1.dto;
import lombok.*;
import java.util.List;


public class ReviewListDto {
    @Builder
    @Getter
    @Setter
    public static class Request {
        private Long productId;
        private Long cursor;
        private int size = 10;
    }

    @Builder
    @Getter
    @Setter
    public static class Response {
        private Long totalCount;
        private Float score;
        private Long cursor;
        private List<ReviewDto.Response> reviewList;
    }
}
