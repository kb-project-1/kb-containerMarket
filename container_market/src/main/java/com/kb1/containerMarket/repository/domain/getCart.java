package com.kb1.containerMarket.repository.domain;

import com.kb1.containerMarket.web.dto.ProductCartRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class getCart {
    private String img;
    private String name;
    private int amount;
    private int price;

    public ProductCartRespDto toDto(){
        return ProductCartRespDto.builder()
                .img(img)
                .name(name)
                .amount(amount)
                .price(price)
                .build();
    }
}
