package com.sparta.corporatechallenge1.service;


import com.sparta.corporatechallenge1.dto.ReviewCreateDto;
import com.sparta.corporatechallenge1.dto.ReviewListDto;

public interface ReviewService {
    void createReview(ReviewCreateDto.Request dto);

    ReviewListDto.Response getReviewList(ReviewListDto.Request dto);
}
