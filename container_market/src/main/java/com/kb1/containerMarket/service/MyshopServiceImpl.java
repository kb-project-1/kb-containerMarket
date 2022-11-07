package com.kb1.containerMarket.service;

import com.kb1.containerMarket.repository.MyshopRepository;
import com.kb1.containerMarket.web.domain.OrderList;
import com.kb1.containerMarket.web.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyshopServiceImpl implements MyshopService {

    private final MyshopRepository myshopRepository;

    @Override
    public List<OrderDto> getOrderListAll(String username) throws Exception {
        List<OrderDto> dtos = new ArrayList<OrderDto>();

        myshopRepository.getOrderListAll(username).forEach(list -> dtos.add(list.toDto()));

        return dtos;
    }
}
