package com.kb1.containerMarket.repository.domain;

import com.kb1.containerMarket.web.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String username;
    private int productId;
    private int count;
    private int status;
    private int status2;
}
