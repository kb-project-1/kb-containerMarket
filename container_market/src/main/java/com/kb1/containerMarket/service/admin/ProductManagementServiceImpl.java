package com.kb1.containerMarket.service.admin;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.repository.admin.ProductManagementRepository;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.admin.AdminProducts;
import com.kb1.containerMarket.web.domain.admin.ProductOption;
import com.kb1.containerMarket.web.domain.admin.ProductSize;
import com.kb1.containerMarket.web.dto.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements  ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryRespDto> getCategoryList() throws Exception {
        List<ProductCategory> categoryList = productManagementRepository.getCategoryList();
        List<CategoryRespDto> categoryRespDtos = categoryList
                .stream().map(ProductCategory::toDto).collect(Collectors.toList());

        return categoryRespDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity())==0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");
        }
    }

    @Override
    public List<AdminProductsRespDto> getProducts(int page) throws Exception {
        page = (page - 1) * 10;
        List<AdminProducts> products = productManagementRepository.getProducts(page);
        List<AdminProductsRespDto> productResponseDtos = products.stream().map(AdminProducts::toDto).collect(Collectors.toList());
        return productResponseDtos;
    }

    @Override
    public List<ProductOptionRespDto> getProductOptions() throws Exception {
        List<ProductOption> productOptions = productManagementRepository.getProductOptions();
        List<ProductOptionRespDto> productOptionRespDtos = productOptions.stream().map(ProductOption::toDto).collect(Collectors.toList());
        return productOptionRespDtos;
    }

    @Override
    public List<ProductSizeRespDto> getProductSize(int productId) throws Exception {
        List<ProductSize> productSizes = productManagementRepository.getProductSize(productId);
        List<ProductSizeRespDto> productSizeRespDtos = productSizes.stream().map(ProductSize::toDto).collect(Collectors.toList());
        return productSizeRespDtos;
    }
}
