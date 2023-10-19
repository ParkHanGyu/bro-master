package com.brogrammers.brogrammers.domain.member;

import com.brogrammers.brogrammers.domain.product.Products;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderProducts {

    @Id @GeneratedValue
    @Column(name="order_Products_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Products products;

}
