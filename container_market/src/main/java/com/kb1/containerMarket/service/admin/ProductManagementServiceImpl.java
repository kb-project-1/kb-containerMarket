package com.kb1.containerMarket.service.admin;

import com.kb1.containerMarket.aop.annotation.LogAspect;
import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.exception.CustomValidationException;
import com.kb1.containerMarket.repository.admin.ProductManagementRepository;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.ProductImg;
import com.kb1.containerMarket.web.domain.admin.AdminProducts;
import com.kb1.containerMarket.web.domain.admin.ProductMst;
import com.kb1.containerMarket.web.domain.admin.ProductOption;
import com.kb1.containerMarket.web.domain.admin.ProductSize;
import com.kb1.containerMarket.web.dto.admin.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements  ProductManagementService {

    private final ProductManagementRepository productManagementRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public List<CategoryRespDto> getCategoryList() throws Exception {
        List<ProductCategory> categoryList = productManagementRepository.getCategoryList();
        List<CategoryRespDto> categoryRespDtos = categoryList
                .stream().map(ProductCategory::toDto).collect(Collectors.toList());

        return categoryRespDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {
        if (productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0) {
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
        if (productManagementRepository.saveProductDtl(productDtlRegisterDto.toEntity()) == 0)
            throw new CustomInternalServerErrorException("상품 등록 오류");
    }

    @Override
    public void checkDuplicatedColor(ProductDtlRegisterDto productDtlRegisterDto) throws Exception {
        if (productManagementRepository.findProductColor(productDtlRegisterDto.toEntity()) > 0) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
            throw new CustomValidationException("Duplicated Error", errorMap);
        }
    }

    @Override
    public void deleteProductMst(int productId) {
        if (productManagementRepository.deleteProductMst(productId) == 0) {
            throw new CustomInternalServerErrorException("상품 삭제 오류");
        }
    }


    @LogAspect
    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
        log.info("pdtId >>> " + productImgReqDto.getPdtId());

        if (productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않았습니다.");
            throw new CustomValidationException("Img Error", errorMap);
        }

        List<ProductImg> productImgs = new ArrayList<ProductImg>();

        productImgReqDto.getFiles().forEach(file -> {
            Resource resource = resourceLoader.getResource("classpath:static/upload/product");
            String filePath = null;

            try {
                if (!resource.exists()) {
                    String tempPath = resourceLoader.getResource("classpath:static").getURI().toString();
                    tempPath = tempPath.substring(tempPath.indexOf("/") + 1);

                    File f = new File(tempPath + "/upload/product");
                    f.mkdirs();
                }
                filePath = resource.getURI().toString();

                filePath = filePath.substring(filePath.indexOf("/") + 1);
                System.out.println(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String originName = file.getOriginalFilename();
            String extension = originName.substring(originName.lastIndexOf("."));
            String saveName = UUID.randomUUID().toString().replaceAll("-", "") + extension;

            Path path = Paths.get(filePath + "/" + saveName);

            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new CustomInternalServerErrorException(e.getMessage());
            }

            productImgs.add(ProductImg.builder()
                    .pdt_id(productImgReqDto.getPdtId())
                    .origin_name(originName)
                    .save_name(saveName)
                    .build());
        });

        productManagementRepository.saveProductImg(productImgs);
    }

    @Override
    public ProductMstRespDto getProductMst(int productId) {
        ProductMst productMst = productManagementRepository.getProductMst(productId);
        return productMst.toDto();
    }

    @Override
    public void updateProductMst(ProductUpdateReqDto productUpdateReqDto) {
        ProductMst productMst = productUpdateReqDto.toEntity();
        if (productManagementRepository.updateProductMst(productMst) != 1)
            throw new CustomInternalServerErrorException("상품 업데이트 오류");
    }
}