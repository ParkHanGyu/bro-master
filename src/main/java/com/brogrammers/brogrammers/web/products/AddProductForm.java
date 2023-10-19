package com.brogrammers.brogrammers.web.products;

import com.brogrammers.brogrammers.domain.member.Member;
import lombok.Data;

@Data
public class AddProductForm {
    private String productBrand;
    private String productName;
    private String productColor;
    private int size;
    private int stockQuantity;

    private int price;
    private Member member; // 등록 회원이 누구인지 식별하기 위한 회원
}
