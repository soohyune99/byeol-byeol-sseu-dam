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
}
