package com.kb1.containerMarket.service.admin;


import com.kb1.containerMarket.web.dto.admin.CategoryResponseDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {
    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;
}
