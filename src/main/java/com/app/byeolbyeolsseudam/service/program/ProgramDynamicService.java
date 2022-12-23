package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProgramDynamicService {

    /* 동적쿼리 */
    public Page<ProgramDTO> programDynamicList(String keyword, String programStatus, Pageable pageable);

}
