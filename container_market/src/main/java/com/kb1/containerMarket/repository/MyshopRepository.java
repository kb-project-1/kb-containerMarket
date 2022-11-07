package com.kb1.containerMarket.repository;

import com.kb1.containerMarket.repository.domain.OrderList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyshopRepository {
    public List<OrderList> getOrderListAll(String username);
}
