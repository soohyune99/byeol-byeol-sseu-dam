package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProgramDynamicService {

    /* 동적쿼리 */
    public Page<ProgramDTO> programDynamicList(String keyword, String programStatus, Pageable pageable);

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    public ProgramDTO findProgramDetail(Long programId);
}
