package com.app.byeolbyeolsseudam.service.program;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.program.ProgramDynamicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProgramDynamicServiceImpl implements ProgramDynamicService {
    private final ProgramDynamicRepository programDynamicRepository;

    /* 동적쿼리 - 검색 => 프로그램 상태 , 프로그램 List */
    @Override
    public Page<ProgramDTO> programDynamicList(String keyword, String programStatus, Pageable pageable) {
        return programDynamicRepository.programDynamicList(keyword, programStatus, pageable);
    }

}
