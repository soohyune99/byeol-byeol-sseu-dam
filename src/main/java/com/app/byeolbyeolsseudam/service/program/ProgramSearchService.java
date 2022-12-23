package com.app.byeolbyeolsseudam.service.program;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.myprogram.Myprogram;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.myprogram.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import com.app.byeolbyeolsseudam.type.MyprogramStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramSearchService implements ProgramService {
    private final ProgramRepository programRepository;
    private final MyprogramRepository myprogramRepository;
    private final MemberRepository memberRepository;

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    @Override
    public ProgramDTO findProgramDetail(Long programId) {
        ProgramDTO programDTO = programRepository.findProgramDetail(programId);

        long count = myprogramRepository.findByProgram_ProgramId(programId).size(); // 마이프로그램

        programDTO.setProgramMemberCount(count); // 신청인원수 까지 넘김

        return programDTO;
    }

    /* 프로그램에 멤버 신청 */
    @Override
    public void programMemberSave(Long programId, Long memberId) {
        MyprogramDTO myprogramDTO = new MyprogramDTO();
        myprogramDTO.setMyprogramStatus(MyprogramStatus.신청완료); // 처음 신청 당시 신청완료로 신청
        myprogramDTO.setProgramId(programId);
        myprogramDTO.setMemberId(memberId);

        Myprogram myprogram = myprogramDTO.toEntity(); // 화면에서 받아온 정보를 entity로 바꾸어 저장
        myprogram.changeProgram(programRepository.findById(programId).get());
        myprogram.changeMember(memberRepository.findById(memberId).get());

        myprogramRepository.save(myprogram);
    }

    /* 프로그램 신청했는지 안했는지 검사 */
    @Override
    public boolean checkMemberAndProgram(Long programId, Long memberId) {
        boolean check = myprogramRepository.checkMemberAndProgram(programId, memberId);

        return check;
    }


}
