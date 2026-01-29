package com.BHstore.repository;

import com.BHstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm đơn hàng theo user (để hiện lịch sử mua hàng)
    List<Order> findByUserId(Long userId);
}