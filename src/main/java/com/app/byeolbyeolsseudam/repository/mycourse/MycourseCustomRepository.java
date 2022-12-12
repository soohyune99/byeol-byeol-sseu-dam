package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;

import java.util.List;

public interface MycourseCustomRepository {
    public List<MycourseDTO> selectMyCourseList(Long memberId);
}
