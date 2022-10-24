package com.kb1.containerMarket.repository.admin;

import com.kb1.containerMarket.web.domain.Product;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.admin.AdminProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManagementRepository {

    public List<ProductCategory> getCategoryList() throws Exception;

    public int saveProductMst(Product product) throws Exception;

    public List<AdminProduct> getProducts(int page) throws Exception;
}
