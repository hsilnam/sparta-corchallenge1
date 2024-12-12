package com.sparta.corporatechallenge1.service;


import com.sparta.corporatechallenge1.dto.ReviewCreateDto;
import com.sparta.corporatechallenge1.dto.ReviewDto;
import com.sparta.corporatechallenge1.dto.ReviewListDto;
import com.sparta.corporatechallenge1.entity.ProductEntity;
import com.sparta.corporatechallenge1.entity.ReviewEntity;
import com.sparta.corporatechallenge1.repository.ProductRepository;
import com.sparta.corporatechallenge1.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void createReview(ReviewCreateDto.Request dto) {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("물건이 존재하지 않습니다")); // TODO: 예외처리

        if (reviewRepository.existsByProductIdAndUserId(dto.getProductId(), dto.getUserId())) {
            throw new IllegalArgumentException("해당 물건에 대한 리뷰를 이미 작성한 유저입니다"); // TODO: 예외처리
        }

        // TODO: 이미지 처리
        String imageUrl = null;
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            imageUrl = "/dummy/" + dto.getImage().getOriginalFilename();
        }

        ReviewEntity review = ReviewEntity.builder()
                .product(product)
                .userId(dto.getUserId())
                .score(dto.getScore())
                .content(dto.getContent())
                .imageUrl(imageUrl)
                .build();

        reviewRepository.save(review);


        long updateReviewCnt = product.getReviewCount() + 1;
        float updateScore = (product.getScore() * product.getReviewCount() + dto.getScore()) / updateReviewCnt;
        product.setReviewCount(updateReviewCnt);
        product.setScore(updateScore);
        productRepository.save(product);
    }

    @Override
    public ReviewListDto.Response getReviewList(ReviewListDto.Request dto) {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("물건이 존재하지 않습니다")); // TODO: 예외처리

        List<ReviewEntity> reviewList = reviewRepository.findByProductIdAndIdGreaterThanOrderByCreatedAtAsc(
                dto.getProductId(),
                (dto.getCursor() == null) ? 0 : dto.getCursor(),
                PageRequest.of(0, dto.getSize()));

        List<ReviewDto.Response> reviewDtoList = reviewList
                .stream()
                .map(review -> ReviewDto.Response.builder()
                        .id(review.getId())
                        .userId(review.getUserId())
                        .score(review.getScore())
                        .content(review.getContent())
                        .imageUrl(review.getImageUrl())
                        .createdAt(review.getCreatedAt())
                        .build()).toList();

        Long nextCursor = (reviewList.isEmpty()) ? null : reviewList.get(reviewList.size() - 1).getId();

        return ReviewListDto.Response.builder()
                .totalCount(product.getReviewCount())
                .score(product.getScore())
                .cursor(nextCursor)
                .reviewList(reviewDtoList)
                .build();
    }


}
