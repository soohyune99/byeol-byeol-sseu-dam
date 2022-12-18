package com.app.byeolbyeolsseudam.repository.myprogram;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.QMyprogramDTO;
import com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram.myprogram;


@Repository
@RequiredArgsConstructor
public class MyprogramCustomRepositoryImpl implements MyprogramCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyprogramDTO> selectMyprogramList(Long memberId, int page){
        return jpaQueryFactory.select(new QMyprogramDTO(myprogram.myprogramId,
                myprogram.myprogramStatus, myprogram.member.memberId, myprogram.program.programId,
                myprogram.program.programName, myprogram.program.programPlace,
                myprogram.program.programDate, myprogram.program.programFileProfileName))
                .from(myprogram)
                .where(myprogram.member.memberId.eq(memberId))
                .orderBy(myprogram.program.programDate.desc())
                .offset(page * 5)
                .limit(5)
                .fetch();
    }
    /* 신청한 프로그램 인지 여부 체크 */
    @Override
    public boolean checkMemberAndProgram(Long programId, Long memberId) {
        boolean check;


        List<MyprogramDTO> myProgramDTOS = jpaQueryFactory.select(new QMyprogramDTO(myprogram.myprogramId,
                myprogram.myprogramStatus, myprogram.member.memberId, myprogram.program.programId,
                myprogram.program.programName, myprogram.program.programPlace,
                myprogram.program.programDate, myprogram.program.programFileProfileName
        )).from(myprogram)
                .where(myprogram.program.programId.eq(programId).and(myprogram.member.memberId.eq(memberId)))
                .fetch();// fetch는 null 이면 빈 문자열 반환

        /* 있는지 없는지 확인 */
        if(myProgramDTOS.size() == 0){ // 신청한지 아닌지
            check = true; // 신청가능
        } else {
            check = false; // 신청불가
        }

        return check;

    }
}
