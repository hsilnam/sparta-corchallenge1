package com.sparta.corporatechallenge1.repository;

import com.sparta.corporatechallenge1.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
