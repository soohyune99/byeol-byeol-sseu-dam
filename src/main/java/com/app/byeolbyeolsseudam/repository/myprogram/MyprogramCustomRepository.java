package com.app.byeolbyeolsseudam.repository.myprogram;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;

import java.util.List;

public interface MyprogramCustomRepository {
    public List<MyprogramDTO> showMyprogramList(Long memberId);
}
