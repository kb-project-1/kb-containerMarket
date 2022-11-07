package com.kb1.containerMarket.repository.domain;

import com.kb1.containerMarket.web.dto.OrderDto;

import java.time.LocalDateTime;

public class OrderList {
    private LocalDateTime order_date;
    private String image_src;
    private String name;
    private int count;
    private int price;
    private String status;
    private String status2;

    public OrderDto toDto(){
        image_src = image_src == null ? "noimage.png" : image_src;
        return OrderDto.builder()
                .order_date(order_date)
                .image_src(image_src)
                .name(name)
                .count(count)
                .price(price)
                .status(status)
                .status2("status2")
                .build();
    }
}
