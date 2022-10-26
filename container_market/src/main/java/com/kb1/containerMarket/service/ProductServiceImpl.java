package com.kb1.containerMarket.service;

import com.kb1.containerMarket.repository.ProductRepository;
import com.kb1.containerMarket.service.ProductService;
import com.kb1.containerMarket.web.domain.Products;
import com.kb1.containerMarket.web.dto.ProductsRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductsRespDto> getProducts(String category, int page) throws Exception {
        List<ProductsRespDto> products = new ArrayList<ProductsRespDto>();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("category",category);
        map.put("index", (page - 1) * 16);

        productRepository.getProducts(map).forEach(product -> {
            products.add(product.toDto());
        });
        return products;
    }
}
