package com.app.byeolbyeolsseudam.repository.admin.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminProgramCustomRepository {
    public List<ProgramDTO> showList();
    public List<ProgramDTO> searchProgram(String keyword);
    public List<ProgramDTO> searchProgramPaging(String keyword, Pageable pageable);
    public ProgramDTO selectById(Long programId);
    public void update(ProgramDTO programDTO);
}
