package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.Search;
import com.app.byeolbyeolsseudam.repository.program.ProgramDynamicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramDynamicServiceImpl implements ProgramDynamicService {
    private final ProgramDynamicRepository programDynamicRepository;

    /* 동적쿼리 */
    @Override
    public List<ProgramDTO> programDynamicList(Search search, Pageable pageable) {
        return programDynamicRepository.programDynamicList(search);
    }

    @Override
    public List<ProgramDTO> programDynamicList(Search search) {
        return null;
    }
}
