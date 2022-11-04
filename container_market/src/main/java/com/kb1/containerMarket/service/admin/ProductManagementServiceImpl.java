package com.kb1.containerMarket.service.admin;

import com.kb1.containerMarket.exception.CustomInternalServerErrorException;
import com.kb1.containerMarket.exception.CustomValidationException;
import com.kb1.containerMarket.repository.admin.ProductManagementRepository;
import com.kb1.containerMarket.web.domain.ProductCategory;
import com.kb1.containerMarket.web.domain.ProductImg;
import com.kb1.containerMarket.web.dto.admin.CategoryResponseDto;
import com.kb1.containerMarket.web.dto.admin.ProductImgReqDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;
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

    private final
    ResourceLoader resourceLoader;

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

    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
        log.info("pdtId >>> " + productImgReqDto.getPdtId());

        if(productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않았습니다.");
            throw new CustomValidationException("Img Error", errorMap);
        }

        List<ProductImg> productImgs = new ArrayList<ProductImg>();

        productImgReqDto.getFiles().forEach(file -> {
            Resource resource = resourceLoader.getResource("classpath:static/upload/product");
            String filePath = null;

            try {
                if(!resource.exists()){
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
}
