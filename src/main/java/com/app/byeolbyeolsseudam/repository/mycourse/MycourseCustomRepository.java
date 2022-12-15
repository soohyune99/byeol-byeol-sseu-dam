package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;

import java.util.List;

public interface MycourseCustomRepository {
    public List<MycourseDTO> selectMyCourseList(Long memberId);
    public MycourseDTO selectMyCourse(Long memberId);
    public int badgeCondition(Long memberId, String keyword);
}
