package com.kb1.containerMarket.repository;

import com.kb1.containerMarket.web.domain.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<Products> getProducts(Map<String, Object> map) throws Exception;
}
