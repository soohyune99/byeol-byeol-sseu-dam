package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.Search;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ProgramDynamicService {

    /* 동적쿼리 - Pageable x */
    public List<ProgramDTO> programDynamicList(Search search);

    /* 동적쿼리 - Pageable o */
    public List<ProgramDTO> programDynamicList(Search search, Pageable pageable);
}
