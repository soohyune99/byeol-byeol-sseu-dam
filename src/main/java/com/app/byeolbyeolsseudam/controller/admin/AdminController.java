package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {

    /* ############################### 공통  ################################### */

    /* 사이드바 */
    @GetMapping("adminsidebar")
    public String adminSideBar(){ return "/app/admin/adminSideBar.html"; }

    /* ############################### 대시 보드  ############################### */

    /* 대시보드 */
    @GetMapping("adminmain")
    public String adminMain(){ return "/app/admin/adminMain.html"; }

    /* ############################### 회원 관리  ############################### */

    //AdminMemberController

    /* ############################### 주문 관리 ################################ */

    /* 주문 관리 - 상품 목록 */
    @GetMapping("adminproductlist")
    public String adminProductList(){ return "/app/admin/adminProductList.html"; }

    /* 주문 관리 - 상품 목록 + 상품 추가 */
    @GetMapping("adminproductadd")
    public String adminProductAdd(){
        return "/app/admin/adminProductAdd.html";
    }

    /* 주문 관리 - 상품 목록 + 상품 수정 */
    @GetMapping("adminproductmodify")
    public String adminProductModify(){ return "/app/admin/adminProductModify.html"; }

    /* 주문 관리 _ 주문 목록 */
    @GetMapping("adminorderlist")
    public String adminOrdertList(){ return "/app/admin/adminOrderList.html"; }

    /* 주문 관리 _ 주문 목록 + 주문 상세 내역 */
    @GetMapping("adminorderdetail")
    public String adminOrderDetail(){ return "/app/admin/adminOrderDetail.html"; }

    /* 주문 관리 _ 리뷰 목록 */
    @GetMapping("adminreviewlist")
    public String adminReviewList(){ return "/app/admin/adminReviewList.html"; }

    /* ############################### 프로그램 관리 ############################# */

//    AdminProgramController

    /* ############################### 줍깅 관리 ################################ */

    /* 줍깅 관리 - 코스 목록 */
    @GetMapping("adminjubjubcourse")
    public String adminJubJubCourse(){
        return "/app/admin/adminJubJubCourse.html";
    }

    /* 줍깅 관리 - 코스 목록 + 코스 추가 */
    @GetMapping("adminjubjubcourseadd")
    public String adminJubJubCourseAdd(){
        return "/app/admin/adminJubJubCourseAdd.html";
    }

    /* 줍깅 관리 - 코스 목록 + 코스 수정 */
    @GetMapping("adminjubjubcoursemodify")
    public String adminJubJubCourseModify(){
        return "/app/admin/adminJubJubCourseModify.html";
    }

    /* 줍깅 관리 - 스팟 목록 */
    @GetMapping("adminjubjubspot")
    public String adminJubJubSpot(){
        return "/app/admin/adminJubJubSpot.html";
    }

    /* 줍깅 관리 - 스팟 목록 + 스팟 추가 */
    @GetMapping("adminjubjubspotadd")
    public String adminJubJubSpotAdd(){
        return "/app/admin/adminJubJubSpotAdd.html";
    }

    /* 줍깅 관리 - 스팟 목록 + 스팟 수정 */
    @GetMapping("adminjubjubspotmodify")
    public String adminJubJubSpotModify(){
        return "/app/admin/adminJubJubSpotModify.html";
    }

    /* 줍깅 관리 - 배지 목록 */
    @GetMapping("adminjubjubbadge")
    public String adminJubJubBadge(){
        return "/app/admin/adminJubJubBadge.html";
    }

    /* 줍깅 관리 - 배지 목록 + 배지 추가 */
    @GetMapping("adminjubjubbadgeadd")
    public String adminJubJubBadgeAdd(){
        return "/app/admin/adminJubJubBadgeAdd.html";
    }

    /* 줍깅 관리 - 배지 목록 + 배지 수정 */
    @GetMapping("adminjubjubbadgemodify")
    public String adminJubJubBadgeModify(){
        return "/app/admin/adminJubJubBadgeModify.html";
    }


    /* ############################### 수거서비스 ############################### */

    /* 수거 서비스 - 수거 목록*/
    @GetMapping("admincollectservice")
    public String adminCollectService(){
        return "/app/admin/adminCollectService.html";
    }

    /* 수거 서비스 - 수거 목록 + 상세 내역 */
    @GetMapping("admincollectservicedetail")
    public String adminCollectServiceDetail(){
        return "/app/admin/adminCollectServiceDetail.html";
    }


    /* ############################### 게시판 관리 ############################## */

    //AdminCommunityController

    /* ############################### 고객 센터 ################################ */

    //AdminNoticeController


    /* ############################### 배너 관리 ############################### */

    //BannerController




}