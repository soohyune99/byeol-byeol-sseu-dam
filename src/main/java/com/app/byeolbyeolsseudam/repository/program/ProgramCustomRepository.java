package com.app.byeolbyeolsseudam.repository.program;


import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;

import java.util.List;

public interface ProgramCustomRepository{

    /* 키워드를 입력시 검색 결과 List */
    public List<ProgramDTO> findAllSearch(String keyword);
}
