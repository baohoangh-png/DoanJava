package com.BHstore.repository;

import com.BHstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Giữ lại cái này để kiểm tra trùng tên khi Đăng ký
    Optional<User> findByUsername(String username);

    // MỚI: Thêm cái này để Đăng nhập bằng Email
    Optional<User> findByEmail(String email);
}