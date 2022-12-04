package com.app.byeolbyeolsseudam.repository.program;


import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.type.ProgramStatus;

import java.util.List;

public interface ProgramCustomRepository{

    /* 키워드를 입력시 검색 결과 List */
    public List<ProgramDTO> searchProgram(String keyword);

    /* 전체 program List */
    public List<ProgramDTO> programAllList();

    /* program List _ STATUS _ 모집중 */
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus);
}
