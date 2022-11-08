package com.kb1.containerMarket.repository.domain;

import com.kb1.containerMarket.web.dto.ProductCartRespDto;
import com.kb1.containerMarket.web.dto.ProductsRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private int id;
    private String user_id;
    private int product_id;
    private int amount;

    public ProductCartRespDto toDto(String img, String pdtName, int price){
        return ProductCartRespDto.builder()
                .img(img)
                .name(pdtName)
                .amount(amount)
                .price(price)
                .build();
    }
}
