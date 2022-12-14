package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.service.admin.AdminBadgeService;
import com.app.byeolbyeolsseudam.service.admin.AdminJubggingService;
import com.app.byeolbyeolsseudam.service.admin.AdminSpotService;
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
@RequestMapping(value = {"/admin/jubgging/*","/admin/jubgging"})
@Slf4j
public class AdminJubggingController {
    private final AdminJubggingService adminJubggingService;
    private final AdminBadgeService adminBadgeService;
    private final AdminSpotService adminSpotService;
    /* 줍깅 관리 - 코스 목록 */
    @GetMapping("/course")
    public String adminJubJubCourse(Model model){
        model.addAttribute("courses", adminJubggingService.showCourse());
        return "/app/admin/adminJubJubCourse";
    }


    /* 줍깅 관리 - 코스 목록 + 코스 추가 */
    @GetMapping("/course/add")
    public String adminJubJubCourseAdd(){

        return "/app/admin/adminJubJubCourseAdd";
    }

    @PostMapping("/course/save")
    public RedirectView adminCourseSave(CourseDTO courseDTO){
        adminJubggingService.saveCourse(courseDTO);

        return new RedirectView("/admin/jubgging/course");
    }

    /* 줍깅 관리 - 코스 목록 + 코스 수정 */
    @GetMapping("/course/modify")
    public String adminJubJubCourseModify(@RequestParam(name = "courseId") String courseId, Model model){
        model.addAttribute("course", adminJubggingService.selectById(courseId));
        return "/app/admin/adminJubJubCourseModify";
    }

    @PostMapping("/course/modified")
    public RedirectView adminJubJubCourseModified(CourseDTO courseDTO){
        log.info("뿌립니다" + courseDTO);
        adminJubggingService.updateCourse(courseDTO,courseDTO.getCourseId());

        return new RedirectView("/admin/jubgging/course");
    }

    @PostMapping("/course/delete")
    public RedirectView adminCourseDelete(@RequestParam List<String> checkedValue){
        adminJubggingService.removeCourse(checkedValue);

        return new RedirectView("/admin/jubgging/course");
    }






    /* 줍깅 관리 - 스팟 목록  페이징*/
    @GetMapping("/spot/{page}")
    public String adminJubJubSpot(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "spotId");

        model.addAttribute("spots", adminSpotService.searchSpot(pageable));
        return "/app/admin/adminJubJubSpot";
    }

    @GetMapping("/spot")
    public String adminSpotSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "spotId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("spots", adminSpotService.searchSpotPaging(keyword,pageable));

        return "/app/admin/adminJubJubSpot";
    }


    /* 줍깅 관리 - 스팟 목록 + 스팟 추가 */
    @GetMapping("/spot/add")
    public String adminJubJubSpotAdd(){
        return "/app/admin/adminJubJubSpotAdd";
    }

    @PostMapping("/spot/save")
    public RedirectView adminSpotSave(SpotDTO spotDTO, String courseName){

        adminJubggingService.saveSpot(spotDTO, courseName);
        return new RedirectView("/admin/jubgging/spot/1");
    }

    /* 줍깅 관리 - 스팟 목록 + 스팟 수정 */
    @GetMapping("/spot/modify")
    public String adminJubJubSpotModify(@RequestParam(name = "spotId") String spotId, Model model){
        model.addAttribute("spot",adminSpotService.selectById(spotId));

        return "/app/admin/adminJubJubSpotModify";
    }

    @PostMapping("/spot/modified")
    public RedirectView adminSpotModified(SpotDTO spotDTO){
        adminSpotService.updateSpot(spotDTO, spotDTO.getSpotId());
        return new RedirectView("/admin/jubgging/spot/1");
    }

    @PostMapping("/spot/delete")
    public RedirectView adminSpotDelete(@RequestParam List<String> checkedValue){
        adminSpotService.removeSpot(checkedValue);
        return new RedirectView("/admin/jubgging/spot/1");

    }


    /* 줍깅 관리 - 배지 목록 페이징*/
    @GetMapping("/badge/{page}")
    public String adminJubJubBadge(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "badgeId");
        model.addAttribute("badges",adminBadgeService.searchBadge(pageable));
        return "/app/admin/adminJubJubBadge";
    }


    @GetMapping("/badge")
    public String adminBadgeSearch(@RequestParam(value = "keyword")String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");
        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "badgeId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("badges", adminBadgeService.searchBadgePaging(keyword, pageable));

        return "/app/admin/adminJubJubBadge";
    }

    /* 줍깅 관리 - 배지 목록 + 배지 추가 */
    @GetMapping("/badge/add")
    public String adminJubJubBadgeAdd(){
        return "/app/admin/adminJubJubBadgeAdd";
    }

    @PostMapping("/badge/save")
    public RedirectView adminBadgeSave(BadgeDTO badgeDTO){
        adminJubggingService.saveBadge(badgeDTO);

        return new RedirectView("/admin/jubgging/badge/1");
    }

    /* 줍깅 관리 - 배지 목록 + 배지 수정 */
    @GetMapping("/badge/modify")
    public String adminJubJubBadgeModify(@RequestParam(name = "badgeId") String badgeId, Model model){
        model.addAttribute("badge", adminBadgeService.selectById(badgeId));

        return "/app/admin/adminJubJubBadgeModify";
    }

    @PostMapping("/badge/modified")
    public RedirectView adminBadgeModified(BadgeDTO badgeDTO){
        adminBadgeService.updateBadge(badgeDTO, badgeDTO.getBadgeId());
        return new RedirectView("/admin/jubgging/badge/1");
    }

    @PostMapping("/badge/delete")
    public RedirectView adminBadgeDelete(@RequestParam List<String> checkedValue){
        adminBadgeService.removeBadge(checkedValue);
        return new RedirectView("/admin/jubgging/badge/1");
    }

}
