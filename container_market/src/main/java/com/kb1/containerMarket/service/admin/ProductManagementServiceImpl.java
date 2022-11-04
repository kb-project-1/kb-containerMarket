package com.kb1.containerMarket.service.admin;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.exception.CustomValidationException;
import com.kb1.containerMarket.repository.admin.ProductManagementRepository;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.admin.AdminProducts;
import com.kb1.containerMarket.web.domain.admin.ProductMst;
import com.kb1.containerMarket.web.domain.admin.ProductOption;
import com.kb1.containerMarket.web.domain.admin.ProductSize;
import com.kb1.containerMarket.web.dto.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public void registerDtl(ProductDtlRegisterDto productDtlRegisterDto) throws Exception {
        if(productManagementRepository.saveProductDtl(productDtlRegisterDto.toEntity()) == 0)
            throw new CustomInternalServerErrorException("상품 등록 오류");
    }

    @Override
    public void checkDuplicatedColor(ProductDtlRegisterDto productDtlRegisterDto) throws Exception {
        if(productManagementRepository.findProductColor(productDtlRegisterDto.toEntity() ) > 0) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
            throw new CustomValidationException("Duplicated Error",errorMap);
        }
    }

    @Override
    public void deleteProductMst(int productId) {
        if(productManagementRepository.deleteProductMst(productId) == 0) {
            throw new CustomInternalServerErrorException("상품 삭제 오류");
        }
    }

    @Override
    public ProductMstRespDto getProductMst(int productId) {
        ProductMst productMst = productManagementRepository.getProductMst(productId);
        return productMst.toDto();
    }
}
