package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramSearchService implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public List<ProgramDTO> searchProgram(String keyword){
        return programRepository.searchProgram(keyword);
    }

    @Override
    public List<ProgramDTO> programAllList() {
        return programRepository.programAllList();
    }

}
