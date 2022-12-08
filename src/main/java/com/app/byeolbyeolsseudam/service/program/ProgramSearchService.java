package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramSearchService implements ProgramService {
    private final ProgramRepository programRepository;

    /* 프로그램 _ Keyword 로 검색 List*/
    @Override
    public List<ProgramDTO> searchProgram(String keyword){
        return programRepository.searchProgram(keyword);
    }

    /* 전체 program List */
    @Override
    public List<ProgramDTO> programAllList() {
        return programRepository.programAllList();
    }

    /* program List _ STATUS List*/
    @Override
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus) {
        return programRepository.programStatusIngList(programStatus);
    }

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    @Override
    public ProgramDTO findProgramDetail(Long programId) {
        ProgramDTO programDTO = programRepository.findProgramDetail(programId);
        return programDTO;
    }

    @Override
    public List<ProgramDTO> selectScrollPrograms(int page) {
        return programRepository.selectScrollPrograms(page);
    }


//    @Override
//    public ProgramDTO programDetailPage(Model model, Member member, Long programId) {
//        return programRepository.programDetailPage(model, member, programId);
//    }
//
//    @Override
//    public ProgramDTO programDetailPage1(Long programId) {
//        return programRepository.programDetailPage1(programId);
//    }


}
