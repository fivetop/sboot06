package com.example.sboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true, length = 45)
    private String title;
    private int price;
    private String author;
    private int page;

    // 책(1) : 리뷰(N)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // 책(1) : 이미지(N)
    @OneToMany(mappedBy = "book", cascade =CascadeType.ALL)
    private List<BookImage> bookImages;

    // 책(1) : 장바구니(N)
    @OneToMany(mappedBy = "book", cascade =CascadeType.ALL)
    private List<Cart> carts;
}
