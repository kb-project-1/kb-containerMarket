package com.kb1.containerMarket.repository.admin;

import com.kb1.containerMarket.web.domain.Product;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.admin.*;
import com.kb1.containerMarket.web.dto.admin.ProductDtlRegisterDto;
import com.kb1.containerMarket.web.dto.admin.ProductMstRespDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManagementRepository {

    public List<ProductCategory> getCategoryList() throws Exception;

    public int saveProductMst(Product product) throws Exception;

    public List<AdminProducts> getProducts(int page) throws Exception;

    public List<ProductOption> getProductOptions() throws Exception;

    public List<ProductSize> getProductSize(int productId) throws Exception;

    public int saveProductDtl(ProductDetail productDetail);

    public int findProductColor(ProductDetail productDetail);

    public int deleteProductMst(int pdt_id);

    public ProductMst getProductMst(int productId);
}
