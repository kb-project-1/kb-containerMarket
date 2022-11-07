package com.kb1.containerMarket.service;

import com.kb1.containerMarket.exception.CustomValidationException;
import com.kb1.containerMarket.repository.ProductRepository;
import com.kb1.containerMarket.service.ProductService;
import com.kb1.containerMarket.web.domain.Product;
import com.kb1.containerMarket.web.domain.Products;
import com.kb1.containerMarket.web.dto.ProductRespDto;
import com.kb1.containerMarket.web.dto.ProductsRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductsRespDto> getProducts(String category, int page) throws Exception {
        List<ProductsRespDto> products = new ArrayList<ProductsRespDto>();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("category",category);
        map.put("index", (page - 1) * 9);

        productRepository.getProducts(map).forEach(product -> {
            products.add(product.toDto());
        });
        return products;
    }

    @Override
    public ProductRespDto getProduct(int pdtId) throws Exception {
        Product product = productRepository.getProduct(pdtId);

        Map<String, List<Map<String, Object>>> pdtColors = new HashMap<String, List<Map<String, Object>>>();

        product.getPdt_dtls().forEach(dtl -> {
            if(!pdtColors.containsKey(dtl.getPdt_color())){
                pdtColors.put(dtl.getPdt_color(), new ArrayList<Map<String, Object>>());
            }
        });
        product.getPdt_dtls().forEach(dtl -> {
            Map<String, Object> pdtDtlIdAndSize = new HashMap<String, Object>();
            pdtDtlIdAndSize.put("pdtDtlId", dtl.getId());
            pdtDtlIdAndSize.put("sizeId", dtl.getSize_id());
            pdtDtlIdAndSize.put("sizeName", dtl.getSize_name());
            pdtDtlIdAndSize.put("pdtStock", dtl.getPdt_stock());

            pdtColors.get(dtl.getPdt_color()).add(pdtDtlIdAndSize);
        });

        ProductRespDto dto = ProductRespDto.builder()
                .pdtId(product.getId())
                .pdtName(product.getPdt_name())
                .pdtPrice(product.getPdt_price())
                .simpleInfo(product.getPdt_simple_info())
                .detailInfo(product.getPdt_detail_info())
                .pdtColors(pdtColors)
                .build();
        return dto;
    }
}
