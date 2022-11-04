package com.kb1.containerMarket.web.api.admin;

import com.kb1.containerMarket.aop.annotation.LogAspect;
import com.kb1.containerMarket.aop.annotation.ValidAspect;
import com.kb1.containerMarket.service.admin.ProductManagementService;
import com.kb1.containerMarket.web.dto.CMRespDto;
import com.kb1.containerMarket.web.dto.admin.ProductDtlRegisterDto;
import com.kb1.containerMarket.web.dto.admin.ProductRegisterReqDto;
import com.kb1.containerMarket.web.dto.admin.ProductUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {

    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?> registerProductMst(@Validated @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                                BindingResult bindingResult) throws Exception {
        productManagementService.registerMst(productRegisterReqDto);
        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully",true));
    }


    @GetMapping("/product/category")
    public ResponseEntity<?> getCategoryList() throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>( "Get successfully", productManagementService.getCategoryList()));
    }

    @LogAspect
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(int page) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>( "Get successfully", productManagementService.getProducts(page)));
    }

    @GetMapping("/option/products/mst")
    public ResponseEntity<?> getProductOptions() throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>( "Get successfully", productManagementService.getProductOptions()));
    }

    @GetMapping("/option/products/size/{productId}")
    public ResponseEntity<?> getProductSize(@PathVariable int productId) throws Exception {
        return ResponseEntity.ok().body(new CMRespDto<>( "Get successfully", productManagementService.getProductSize(productId)));
    }

    @PostMapping("/product/dtl")
    public ResponseEntity<?> registerdtl(@RequestBody ProductDtlRegisterDto productDtlRegisterDto) throws Exception
    {
        productManagementService.checkDuplicatedColor(productDtlRegisterDto);
        productManagementService.registerDtl(productDtlRegisterDto);
        return ResponseEntity.ok().body(new CMRespDto<>( "Register successfully", true));
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProductMst(@PathVariable int productId) {
        productManagementService.deleteProductMst(productId);
        return ResponseEntity.ok().body(new CMRespDto<>("Delete Successfully", true));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductMst(@PathVariable int productId) {

        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully", productManagementService.getProductMst(productId)));
    }

    @PatchMapping("/product")
    public ResponseEntity<?> updateProductMst(@RequestBody  ProductUpdateReqDto productUpdateReqDto) {
        productManagementService.updateProductMst(productUpdateReqDto);
        return ResponseEntity.ok().body(new CMRespDto<>("Update Successfully", true));
    }
}
