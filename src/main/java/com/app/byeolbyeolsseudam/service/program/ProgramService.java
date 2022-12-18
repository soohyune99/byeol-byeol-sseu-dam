package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.Search;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Service
public interface ProgramService {

    /* 프로그램 _ Keyword 로 검색 List*/
    public List<ProgramDTO> searchProgram(String keyword);

    /* 전체 program List */
    public List<ProgramDTO> programAllList();

    /* program List _ STATUS List*/
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus);

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    public ProgramDTO findProgramDetail(Long programId);

//    /* 무한 스크롤 - 동적 쿼리 */
//    public Page<ProgramDTO> selectScrollPrograms(Search search, Pageable pageable);

    /* program Detail _ 상세보기 클릭시 상세페이지 */
    public ProgramDTO programDetailPage(Model model, Member member, Long programId);

    public ProgramDTO programDetailPage1(Long programId);


    /* 프로그램에 멤버 신청 */
    public void programMemberSave(Long programId, Long memberId);
    /* 프로그램 신청했는지 안했는지 검사 */
    public boolean checkMemberAndProgram(Long programId, Long memberId);
}
