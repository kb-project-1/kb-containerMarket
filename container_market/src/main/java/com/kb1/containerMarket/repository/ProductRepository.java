package com.kb1.containerMarket.repository;

import com.kb1.containerMarket.repository.domain.Cart;
import com.kb1.containerMarket.repository.domain.Product;
import com.kb1.containerMarket.repository.domain.Products;
import com.kb1.containerMarket.repository.domain.getCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<Products> getProducts(Map<String, Object> map) throws Exception;
    public Product getProduct(int pdtId) throws Exception;
    public boolean addProductCart(Cart cart) throws Exception;
    public List<getCart> getProductCart(String username) throws Exception;
}
