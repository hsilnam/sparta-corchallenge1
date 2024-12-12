package com.sparta.corporatechallenge1.contorller;

import com.sparta.corporatechallenge1.dto.ReviewCreateDto;
import com.sparta.corporatechallenge1.dto.ReviewListDto;
import com.sparta.corporatechallenge1.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Void> createReview(
            @PathVariable Long productId,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @ModelAttribute ReviewCreateDto.Request dto
    ) {
        dto.setProductId(productId);
        dto.setImage(image);
        reviewService.createReview(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ReviewListDto.Response getReviewList(
            @PathVariable Long productId,
            @ModelAttribute ReviewListDto.Request dto
    ) {
        dto.setProductId(productId);
        ReviewListDto.Response response = reviewService.getReviewList(dto);
        return response;
    }
}
