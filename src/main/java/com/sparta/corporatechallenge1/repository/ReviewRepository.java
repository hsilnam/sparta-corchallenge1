package com.sparta.corporatechallenge1.repository;

import com.sparta.corporatechallenge1.entity.ReviewEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<ReviewEntity> findByProductIdAndIdGreaterThanOrderByCreatedAtAsc(
            Long productId,
            Long cursor,
            PageRequest pageable
    );
}
