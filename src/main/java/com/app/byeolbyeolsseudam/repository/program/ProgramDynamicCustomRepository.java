package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramDynamicCustomRepository {

    /* 동적쿼리 - 검색 => 프로그램 상태 , 프로그램 List */
    public Page<ProgramDTO> programDynamicList(String keyword, String programStatus, Pageable pageable);

}
