package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.Search;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class ProgramCustomRepositoryImpl implements ProgramCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /* 프로그램 Article 클릭시 해당 Detail 페이지로 이동 */
    @Override
    public ProgramDTO findProgramDetail(Long programId) {

        ProgramDTO programDTO = jpaQueryFactory.select(new QProgramDTO(
                program.programId,
                program.programName,
                program.programPlace,
                program.possibleDate.openingDate,
                program.possibleDate.closingDate,
                program.programTime,
                program.programDate,
                program.programContent,
                program.programLimitCount,
                program.programStatus,
                program.programFileProfileName,
                program.programFileProfilePath,
                program.programFileProfileUuid,
                program.programFileDetailName,
                program.programFileDetailPath,
                program.programFileDetailUuid,
                program.createdDate
        )).from(program)
                .where(program.programId.eq(programId))
                .fetchOne();

        return programDTO;
    }
}