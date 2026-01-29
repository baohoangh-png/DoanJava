package com.BHstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

    private String category;

    // Chỉ rõ tên cột để Hibernate không nhầm lẫn
    @Column(name = "gender")
    private String gender;
}