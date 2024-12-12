package com.sparta.corporatechallenge1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Review")
// uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "userId"}) // 상품-유저 중복 방지uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "userId"}) // 상품-유저 중복 방지
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private String imageUrl;
}
