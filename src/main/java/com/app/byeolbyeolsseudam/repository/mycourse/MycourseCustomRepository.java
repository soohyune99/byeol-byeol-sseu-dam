package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;

import java.util.List;

public interface MycourseCustomRepository {
    public List<MycourseDTO> selectMyCourseList(Long memberId);
    public List<MycourseDTO> selectMyCourse(Long memberId, String courseName, int SpotNumber);
}
