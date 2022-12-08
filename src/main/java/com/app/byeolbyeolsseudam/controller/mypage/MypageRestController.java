package com.app.byeolbyeolsseudam.controller.mypage;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MypageRestController {
    private final MypageService mypageService;

    @GetMapping("/program/{memberId}")
    public List<MyprogramDTO> getMyprogramList(@PathVariable Long memberId){
        return mypageService.showMyprogramList(memberId);
    }
}
