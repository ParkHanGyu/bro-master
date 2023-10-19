package com.brogrammers.brogrammers.domain.product;

import javax.persistence.*;

@Entity
public class ProductsCategory {
    @Id @GeneratedValue
    @Column(name="products_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="products_id")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

}
