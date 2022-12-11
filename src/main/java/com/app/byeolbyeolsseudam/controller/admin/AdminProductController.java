package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.service.admin.AdminProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/product/*","/admin/product"})
@Slf4j
public class AdminProductController {
    private final AdminProductService adminProductService;

    /* 주문 관리 - 상품 목록 */
    @GetMapping("/{page}")
    public String adminProductList(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "productId");

        model.addAttribute("products", adminProductService.searchProduct(pageable));
        return "/app/admin/adminProductList"; }

    @GetMapping("")
    public String adminProductSearchList(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "boardId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("products", adminProductService.searchProductPaging(keyword,pageable));
        return "/app/admin/adminProductList"; }

    @PostMapping("/delete")
    public RedirectView adminProductDelete(@RequestParam List<String> checkedValue){
        adminProductService.removeProduct(checkedValue);

        return new RedirectView("/admin/product/1");

    }

    /* 주문 관리 - 상품 목록 + 상품 추가 */
    @GetMapping("/add")
    public String adminProductAdd(){
        return "/app/admin/adminProductAdd";
    }

    @PostMapping("/save")
    public RedirectView adminProductSave(ProductDTO productDTO){
        adminProductService.saveProduct(productDTO);
        return new RedirectView("/admin/product/1");
    }

    /* 주문 관리 - 상품 목록 + 상품 수정 */
    @GetMapping("/modify")
    public String adminProductModify(@RequestParam(name = "productId") Long productId, Model model){
        model.addAttribute("product", adminProductService.selectById(productId));
        return "/app/admin/adminProductModify"; }

    @PostMapping("/modified")
    public RedirectView adminProductModified(ProductDTO productDTO){
        adminProductService.updateProduct(productDTO, productDTO.getProductId());
        return new RedirectView("/admin/product/1"); }


    /* 주문 관리 _ 주문 목록 */
    @GetMapping("/order")
    public String adminOrderList(){ return "/app/admin/adminOrderList"; }

    /* 주문 관리 _ 주문 목록 + 주문 상세 내역 */
    @GetMapping("/order/detail")
    public String adminOrderDetail(){ return "/app/admin/adminOrderDetail"; }

    /* 주문 관리 _ 리뷰 목록 */
    @GetMapping("review")
    public String adminReviewList(){ return "/app/admin/adminReviewList"; }

}
