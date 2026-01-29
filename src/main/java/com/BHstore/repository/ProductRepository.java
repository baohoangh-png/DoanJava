package com.BHstore.repository;

import com.BHstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Interface này giúp bạn lấy dữ liệu (findAll, save, delete...) mà không cần
    // viết SQL
}