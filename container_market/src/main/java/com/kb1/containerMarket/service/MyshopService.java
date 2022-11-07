package com.kb1.containerMarket.service;

import com.kb1.containerMarket.web.dto.OrderDto;

import java.util.List;

public interface MyshopService {
    List<OrderDto> getOrderListAll(String username) throws Exception;

}
