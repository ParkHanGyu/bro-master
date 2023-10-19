package com.brogrammers.brogrammers.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Products> productList = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<ProductsCategory> productsCategoryList = new ArrayList<>();
}