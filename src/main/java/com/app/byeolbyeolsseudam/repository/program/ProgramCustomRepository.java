package com.app.byeolbyeolsseudam.repository.program;


import com.app.byeolbyeolsseudam.domain.Search;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

public interface ProgramCustomRepository{

    /* 키워드를 입력시 검색 결과 List */
    public List<ProgramDTO> searchProgram(String keyword);

    /* 전체 program List */
    public List<ProgramDTO> programAllList();

    /* program List _ STATUS List */
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus);

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    public ProgramDTO findProgramDetail(Long programId);

    public Page<ProgramDTO> selectScrollPrograms (Search search, Pageable pageable);


//    /* program Detail _ 상세보기 클릭시 상세페이지 */
//    public ProgramDTO programDetailPage(Model model, @SessionAttribute Member member, Long programId);
//
//    /* program Detail _ 상세보기 클릭시 상세페이지 */
//    public ProgramDTO programDetailPage1(Long programId);

}
