package com.brogrammers.brogrammers.domain.repository;

import com.brogrammers.brogrammers.domain.member.Member;
import com.brogrammers.brogrammers.domain.product.Products;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductsRepository {

    @PersistenceContext
    private EntityManager em;


    public void productsSave(Products products){
        em.persist(products);
    } // 상품 저장 메서드

    public List<Products> findAll(){
        return em.createQuery("select p from Products p ",Products.class).getResultList();
    }

    public Products findById(Long id){
        return em.find(Products.class,id);
    }


}
