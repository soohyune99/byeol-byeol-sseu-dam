package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;

import java.util.List;
import java.util.Optional;

public interface MycourseCustomRepository {
    public List<MycourseDTO> selectMyCourseList(Long memberId);
    public MycourseDTO selectMyCourse(Long memberId);
    public Mycourse findActiveCourse(MemberDTO memberDTO, String courseName);
    public int badgeCondition(Long memberId, String keyword);
}
