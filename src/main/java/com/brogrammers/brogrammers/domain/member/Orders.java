package com.brogrammers.brogrammers.domain.member;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Orders {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="orderDate")
    private LocalDateTime orderDate;
}
