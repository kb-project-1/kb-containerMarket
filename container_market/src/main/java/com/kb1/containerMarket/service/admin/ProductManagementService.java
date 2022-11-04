package com.kb1.containerMarket.service.admin;


import com.kb1.containerMarket.web.dto.admin.*;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryRespDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

    public List<AdminProductsRespDto> getProducts(int page) throws Exception;

    public List<ProductOptionRespDto> getProductOptions() throws Exception;

    public List<ProductSizeRespDto> getProductSize(int productId) throws Exception;

    public void registerDtl(ProductDtlRegisterDto productDtlRegisterDto) throws Exception;

    public void checkDuplicatedColor(ProductDtlRegisterDto productDtlRegisterDto) throws Exception;

    public void deleteProductMst(int productId);
}
