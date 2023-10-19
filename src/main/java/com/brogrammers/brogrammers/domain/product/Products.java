package com.brogrammers.brogrammers.domain.product;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.member.OrderProducts;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Products {

    @Id @GeneratedValue
    @Column(name="products_id")
    private Long id; // ID, DB에서 자동 주입
    @Column(name="products_name")
    private String name; // 상품 이름,
    @Column(name="products_brand")
    private String brand; // 상품 브랜드
    @Column(name="products_color")
    private String color; // 색상
    @Column(name="products_price", columnDefinition = "integer default 0")
    private int price; // 가격
    @Column(name="products_size")
    private int size; // 사이즈
    @Column(name="products_stockQuantity")
    private int stockQuantity;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
    // 이미지 저장 컬럼도 만들어야함. 아직 어떻게 하는 지 모름

    public Products saveProduct(String name, String brand,String color ,int price, int size, int stockQuantity,Member member){
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.size = size;
        this.member = member;
        this.stockQuantity = stockQuantity;
        return this;
    } // 상품 저장할 때 사용하는

    @Column(name="reg_LocalDate")
    private LocalDate regDate; // 자동 주입되게

    @PrePersist
    public void prePersist() {
        // 엔티티가 영구 저장되기 전에 실행되는 메서드
        this.regDate = LocalDate.now(); // 현재 날짜와 시간을 설정
    }

    @OneToMany(mappedBy = "products") // 다대다 관계 아직 덜 공부됨.
    List<ProductsCategory> productsCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "products")
    List<OrderProducts> orderProducts = new ArrayList<>();

}
