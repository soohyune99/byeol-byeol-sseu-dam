package com.app.byeolbyeolsseudam.repository.myprogram;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;

import java.util.List;

public interface MyprogramCustomRepository {
    public List<MyprogramDTO> selectMyprogramList(Long memberId, int page);

    /* 신청한 프로그램인 지 체크 */
    public boolean checkMemberAndProgram(Long programId, Long memberId);

}
