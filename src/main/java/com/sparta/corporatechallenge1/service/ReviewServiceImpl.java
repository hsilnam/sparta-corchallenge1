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

    }

    @Override
    public ReviewListDto.Response getReviewList(ReviewListDto.Request dto) {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new IllegalArgumentException("물건이 존재하지 않습니다")); // TODO: 예외처리

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

        Long nextCursor = (reviewList.isEmpty())? null: reviewList.get(reviewList.size()-1).getId();

        return ReviewListDto.Response.builder()
                .totalCount(product.getReviewCount())
                .score(product.getScore())
                .cursor(nextCursor)
                .reviewList(reviewDtoList)
                .build();
    }


}
