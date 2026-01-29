package com.BHstore.controller;

import com.BHstore.model.Product;
import com.BHstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 1. API lấy danh sách giày
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. API lấy chi tiết
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    // 3. API Thêm mới sản phẩm
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Spring Boot sẽ tự động map tất cả các trường từ JSON vào Object Product
        // bao gồm cả category và gender nếu tên trường khớp nhau.
        return productRepository.save(product);
    }

    // 4. API Sửa sản phẩm (ĐÃ CẬP NHẬT ĐỂ LƯU GIỚI TÍNH)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật các thông tin cơ bản
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setImage(productDetails.getImage());
        product.setDescription(productDetails.getDescription());
        product.setCategory(productDetails.getCategory());

        // --- CỰC KỲ QUAN TRỌNG: THÊM DÒNG NÀY ĐỂ LƯU GIỚI TÍNH ---
        // Nếu không có dòng này, cột gender trong DB sẽ không bao giờ thay đổi khi sửa
        product.setGender(productDetails.getGender());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // 5. API Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}