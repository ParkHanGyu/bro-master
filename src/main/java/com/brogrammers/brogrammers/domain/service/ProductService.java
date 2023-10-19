package com.brogrammers.brogrammers.domain.service;

import com.brogrammers.brogrammers.domain.product.Products;
import com.brogrammers.brogrammers.domain.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;

    @Transactional
    public void save(Products products){
        productsRepository.productsSave(products);
    }

    public List<Products> findAll(){
        return productsRepository.findAll();
    }

    public Products findById(Long id){return  productsRepository.findById(id);}

}
