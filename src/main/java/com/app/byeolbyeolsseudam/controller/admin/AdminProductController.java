package com.app.byeolbyeolsseudam.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/product/*","/admin/product"})
@Slf4j
public class AdminProductController {


    /* 주문 관리 - 상품 목록 */
    @GetMapping("")
    public String adminProductList(){ return "/app/admin/adminProductList"; }

    /* 주문 관리 - 상품 목록 + 상품 추가 */
    @GetMapping("/add")
    public String adminProductAdd(){
        return "/app/admin/adminProductAdd";
    }

    /* 주문 관리 - 상품 목록 + 상품 수정 */
    @GetMapping("/modify")
    public String adminProductModify(){ return "/app/admin/adminProductModify"; }

    /* 주문 관리 _ 주문 목록 */
    @GetMapping("/order")
    public String adminOrdertList(){ return "/app/admin/adminOrderList"; }

    /* 주문 관리 _ 주문 목록 + 주문 상세 내역 */
    @GetMapping("/order/detail")
    public String adminOrderDetail(){ return "/app/admin/adminOrderDetail"; }

    /* 주문 관리 _ 리뷰 목록 */
    @GetMapping("/review")
    public String adminReviewList(){ return "/app/admin/adminReviewList"; }

}
