package com.BHstore.service;

import com.BHstore.model.Order;
import com.BHstore.model.OrderDetail;
import com.BHstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Thêm cái này để an toàn dữ liệu

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 1. Lưu đơn hàng (Xử lý logic nối bảng)
    @Transactional // Nếu lỗi thì rollback, không lưu rác
    public Order saveOrder(Order order) {
        order.setOrderDate(new Date());
        order.setStatus("PENDING");

        // Gán order cho từng chi tiết
        if (order.getOrderDetails() != null) {
            for (OrderDetail detail : order.getOrderDetails()) {
                detail.setOrder(order);
            }
        }
        return orderRepository.save(order);
    }

    // 2. Lấy danh sách theo User
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // 3. Lấy tất cả (Cho Admin)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    // 5. Xóa đơn hàng
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}