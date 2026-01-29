package com.BHstore.controller;

import com.BHstore.model.Product;
import com.BHstore.repository.OrderRepository;
import com.BHstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    // --- PHẦN CŨ (Giữ nguyên) ---
    @GetMapping
    public String dashboard() {
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders")
    public String manageOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orders";
    }

    // --- PHẦN MỚI THÊM VÀO ---

    // 1. Xem danh sách sản phẩm
    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin/products";
    }

    // 2. Hiện form thêm mới
    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product()); // Gửi một object rỗng sang form
        return "admin/product-add";
    }

    // 3. Hiện form sửa (Lấy dữ liệu cũ đổ vào form)
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "admin/product-add"; // Dùng chung form với thêm mới
    }

    // 4. Lưu sản phẩm (Chạy khi bấm nút Save)
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products"; // Lưu xong quay về danh sách
    }

    // 5. Xóa sản phẩm
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}