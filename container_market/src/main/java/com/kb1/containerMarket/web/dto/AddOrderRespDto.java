package com.kb1.containerMarket.web.dto;

import com.kb1.containerMarket.repository.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Builder
@Data
public class AddOrderRespDto {
    private String user_id;
    private String pdtId;
    private String amount;

    public Order toEntity(){
        return Order.builder()
                .username(user_id)
                .productId(Integer.parseInt(pdtId))
                .count(Integer.parseInt(amount))
                .status(1)
                .build();
    }
}
