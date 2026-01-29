package com.BHstore.controller;

import com.BHstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/") // Khi người dùng vào trang chủ (http://localhost:8081)
    public String home(Model model) {
        // Lấy danh sách giày từ Database và gửi sang giao diện
        model.addAttribute("products", productRepository.findAll());
        return "index"; // Trả về file index.html
    }
}