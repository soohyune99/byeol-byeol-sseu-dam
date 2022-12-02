package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgramService {

    /* 프로그램 _ Keyword 로 검색시 해당 _ 보류 */
    public List<ProgramDTO> searchProgram(String keyword);

    /* 전체 program List */
    public List<ProgramDTO> programAllList();

}
