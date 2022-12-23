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

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    public ProgramDTO findProgramDetail(Long programId);

    /* 프로그램에 멤버 신청 */
    public void programMemberSave(Long programId, Long memberId);

    /* 프로그램 신청했는지 안했는지 검사 */
    public boolean checkMemberAndProgram(Long programId, Long memberId);
}
