package com.kb1.containerMarket.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class OrderDto {
    private LocalDateTime order_date;
    private String image_src;
    private String name;
    private int count;
    private int price;
    private String status;
    private String status2;
}
