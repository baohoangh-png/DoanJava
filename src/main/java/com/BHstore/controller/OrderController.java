package com.BHstore.controller;

import com.BHstore.model.Order;
import com.BHstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000") // Đảm bảo React kết nối được
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 1. API Đặt hàng (Giữ nguyên - Đã tốt)
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.saveOrder(order);
            // Trả về đối tượng JSON thay vì chuỗi thuần để Frontend dễ xử lý ID
            return ResponseEntity.ok(Map.of(
                    "message", "Đặt hàng thành công!",
                    "orderId", savedOrder.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi đặt hàng: " + e.getMessage());
        }
    }

    // 2. API Lấy lịch sử đơn hàng của 1 khách hàng (Dùng cho trang Quản lý khách
    // hàng)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // 3. API Lấy tất cả đơn hàng (Cho Admin Quản lý đơn hàng)
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // 4. API Cập nhật trạng thái (Duyệt đơn)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            // Xử lý chuỗi status bị dư dấu ngoặc kép từ phía Frontend gửi lên
            String cleanStatus = status.replace("\"", "").trim();
            orderService.updateOrderStatus(id, cleanStatus);
            return ResponseEntity.ok(Map.of("message", "Cập nhật trạng thái thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi cập nhật: " + e.getMessage());
        }
    }

    // 5. API Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok(Map.of("message", "Xóa đơn hàng thành công"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi xóa đơn hàng");
        }
    }
}