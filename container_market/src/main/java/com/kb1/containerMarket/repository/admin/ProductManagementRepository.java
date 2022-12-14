package com.kb1.containerMarket.repository.admin;

import com.kb1.containerMarket.repository.domain.Product;
import com.kb1.containerMarket.repository.domain.ProductCategory;
import com.kb1.containerMarket.repository.domain.ProductImg;
import com.kb1.containerMarket.repository.domain.admin.*;
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

    public int updateProductMst(ProductMst productMst);

    public int saveProductImg(List<ProductImg> productImg) throws Exception;
}
