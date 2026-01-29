package com.BHstore.service;

import com.BHstore.model.CartItem;
import com.BHstore.model.Product;
import com.BHstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope // Quan trọng: Giỏ hàng sống theo phiên làm việc của user
public class CartService {

    private List<CartItem> items = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    // 1. Thêm sản phẩm vào giỏ
    public void addToCart(Long productId, int quantity) {
        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Nếu chưa có, tìm trong DB và thêm mới
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            items.add(new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    quantity,
                    product.getImage()));
        }
    }

    // 2. Lấy danh sách sản phẩm
    public List<CartItem> getItems() {
        return items;
    }

    // 3. Xóa sản phẩm khỏi giỏ
    public void removeFromCart(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    // 4. Xóa sạch giỏ hàng
    public void clearCart() {
        items.clear();
    }

    // 5. Tính tổng tiền
    public double getTotalAmount() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}