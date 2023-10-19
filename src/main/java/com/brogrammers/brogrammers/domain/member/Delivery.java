package com.brogrammers.brogrammers.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Delivery {
    @Id @GeneratedValue
    @Column(name="dilivery_id")
    private Long id;
}
