package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramSearchService implements ProgramService {
    private final ProgramRepository programRepository;

    /* 프로그램 _ Keyword 로 검색시 해당 _ 보류 */
    @Override
    public List<ProgramDTO> searchProgram(String keyword){
        return programRepository.searchProgram(keyword);
    }

    /* 전체 program List */
    @Override
    public List<ProgramDTO> programAllList() {
        return programRepository.programAllList();
    }

    /* program List _ STATUS _ 모집중 */
    @Override
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus) {
        return programRepository.programStatusIngList(programStatus);
    }

}
