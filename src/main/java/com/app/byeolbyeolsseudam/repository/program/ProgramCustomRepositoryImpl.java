package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.program.QProgram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProgramCustomRepositoryImpl implements ProgramCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /* 키워드를 입력시 검색 결과 List */
    @Override
    public List<ProgramDTO> findAllSearch(String keyword) {
        return jpaQueryFactory.select(new QProgramDTO(
                QProgram.program.programId,
                QProgram.program.programName,
                QProgram.program.programPlace,
                QProgram.program.possibleDate.openingDate,
                QProgram.program.possibleDate.closingDate,
                QProgram.program.programTime,
                QProgram.program.programDate,
                QProgram.program.programContent,
                QProgram.program.programLimitCount,
                QProgram.program.programStatus,
                QProgram.program.programFileProfileName,
                QProgram.program.programFileProfilePath,
                QProgram.program.programFileProfileUuid,
                QProgram.program.programFileDetailName,
                QProgram.program.programFileDetailPath,
                QProgram.program.programFileDetailUuid,
                QProgram.program.createdDate
        )).from(QProgram.program)
                .where(QProgram.program.programName.contains(keyword))
                .fetch();
    }
}
