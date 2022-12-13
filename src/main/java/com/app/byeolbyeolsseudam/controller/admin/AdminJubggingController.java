package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.service.admin.AdminJubggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/jubgging/*","/admin/jubgging"})
@Slf4j
public class AdminJubggingController {
    private final AdminJubggingService adminJubggingService;

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
    @GetMapping("/spot")
    public String adminJubJubSpot(){
        return "/app/admin/adminJubJubSpot";
    }

    /* 줍깅 관리 - 스팟 목록 + 스팟 추가 */
    @GetMapping("/spot/add")
    public String adminJubJubSpotAdd(){
        return "/app/admin/adminJubJubSpotAdd";
    }

    /* 줍깅 관리 - 스팟 목록 + 스팟 수정 */
    @GetMapping("/spot/modify")
    public String adminJubJubSpotModify(){
        return "/app/admin/adminJubJubSpotModify";
    }




    /* 줍깅 관리 - 배지 목록 페이징*/
    @GetMapping("/badge")
    public String adminJubJubBadge(){
        return "/app/admin/adminJubJubBadge";
    }

    /* 줍깅 관리 - 배지 목록 + 배지 추가 */
    @GetMapping("/badge/add")
    public String adminJubJubBadgeAdd(){
        return "/app/admin/adminJubJubBadgeAdd";
    }

    /* 줍깅 관리 - 배지 목록 + 배지 수정 */
    @GetMapping("/badge/modify")
    public String adminJubJubBadgeModify(){
        return "/app/admin/adminJubJubBadgeModify";
    }
}
