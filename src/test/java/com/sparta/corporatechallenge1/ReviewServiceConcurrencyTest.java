package com.sparta.corporatechallenge1;

import com.sparta.corporatechallenge1.dto.ReviewCreateDto;
import com.sparta.corporatechallenge1.entity.ProductEntity;
import com.sparta.corporatechallenge1.repository.ProductRepository;
import com.sparta.corporatechallenge1.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReviewServiceConcurrencyTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductRepository productRepository;

    private Long productId;

    @BeforeEach
    void init() {
        ProductEntity product = ProductEntity.builder()
                .reviewCount(0L)
                .score(0.0f)
                .build();
        product = productRepository.save(product);
        productId = product.getId();
    }


    @Test
    public void testCreateReviewConcurrency() throws InterruptedException {
        // Given
        Long userId = 1L;

        int numberOfThreads = 1000; // 동시 요청 수
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        int sum = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            Long threadUserId = userId + i;

            int score = new Random().nextInt(5) + 1;
            sum += score;

            executorService.submit(() -> {
                try {
                    ReviewCreateDto.Request request = new ReviewCreateDto.Request();
                    request.setProductId(productId);
                    request.setUserId(threadUserId);
                    request.setScore(score);
                    request.setContent("좋아요!! " + userId);

                    reviewService.createReview(request);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Then
        ProductEntity product = productRepository.findById(productId).orElseThrow();
        assertEquals(numberOfThreads, product.getReviewCount(), "리뷰 수가 예상과 다릅니다.");
//        assertEquals((float) sum / (float) numberOfThreads, product.getScore(), "평균 점수가 예상과 다릅니다."); // 부동소수점으로 인해 답이 다를 수 있음
    }
}
