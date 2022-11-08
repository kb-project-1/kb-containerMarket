package com.kb1.containerMarket.service;

import com.kb1.containerMarket.web.dto.AddOrderRespDto;
import com.kb1.containerMarket.web.dto.ProductCartRespDto;
import com.kb1.containerMarket.web.dto.ProductRespDto;
import com.kb1.containerMarket.web.dto.ProductsRespDto;

import java.util.List;

public interface ProductService {
    List<ProductsRespDto> getProducts(String category, int page) throws Exception;

    public ProductRespDto getProduct(int pdtId) throws Exception;
    public boolean addProductCart(int pdtId, String username) throws Exception;
    public List<ProductCartRespDto> getProductCart(String username) throws Exception;
    public boolean addOrder(AddOrderRespDto product) throws Exception;
}
