package com.app.byeolbyeolsseudam.repository.mypoint;

import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;

import java.util.List;

public interface MypointCustomRepository {
    public List<MypointDTO> showMypointList(Long memberId, int page);
}
