package com.kb1.containerMarket.service.admin;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.repository.admin.ProductManagementRepository;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.dto.admin.CategoryResponseDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements  ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        List<ProductCategory> categoryList = productManagementRepository.getCategoryList();
        List<CategoryResponseDto> categoryResponseDtos = categoryList
                .stream().map(ProductCategory::toDto).collect(Collectors.toList());

        return categoryResponseDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity())==0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");
        }
    }
}
