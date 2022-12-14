package com.kb1.containerMarket.service;

import com.kb1.containerMarket.repository.ProductRepository;
import com.kb1.containerMarket.repository.domain.Cart;
import com.kb1.containerMarket.repository.domain.Product;
import com.kb1.containerMarket.web.dto.AddOrderRespDto;
import com.kb1.containerMarket.web.dto.ProductCartRespDto;
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

        System.out.println("product.getSave_name() = " + product.getSave_name());
        ProductRespDto dto = ProductRespDto.builder()
                .pdtId(product.getId())
                .pdtName(product.getPdt_name())
                .pdtPrice(product.getPdt_price())
                .simpleInfo(product.getPdt_simple_info())
                .detailInfo(product.getPdt_detail_info())
                .pdtColors(pdtColors)
                .pdtImg(product.getSave_name())
                .build();
        return dto;
    }

    @Override
    public boolean addProductCart(int pdtId, String username) throws Exception {
        return productRepository.addProductCart(Cart.builder()
                        .product_id(pdtId)
                        .user_id(username)
                        .amount(1)
                        .build());
    }

    @Override
    public List<ProductCartRespDto> getProductCart(String username) throws Exception {
        List<ProductCartRespDto> dto = new ArrayList<ProductCartRespDto>();
        productRepository.getProductCart(username).forEach(entity -> {
            dto.add(entity.toDto());
        });
        return dto;
    }

    @Override
    public boolean addOrder(AddOrderRespDto product) throws Exception {
        return productRepository.addOrder(product.toEntity());
    }
}
