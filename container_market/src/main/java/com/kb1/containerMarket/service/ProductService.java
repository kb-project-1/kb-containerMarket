package com.kb1.containerMarket.service;

import com.kb1.containerMarket.web.domain.Product;
import com.kb1.containerMarket.web.dto.ProductsRespDto;

import java.util.List;

public interface ProductService {
    List<ProductsRespDto> getProducts(String category, int page) throws Exception;
}
