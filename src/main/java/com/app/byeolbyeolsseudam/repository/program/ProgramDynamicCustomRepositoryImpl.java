package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class ProgramDynamicCustomRepositoryImpl implements ProgramDynamicCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /* 동적쿼리 - 검색 => 프로그램 상태 , 프로그램 List */
    @Override
    public Page<ProgramDTO> programDynamicList (String keyword, String programStatus, Pageable pageable) {
        List<ProgramDTO> programs = jpaQueryFactory.select(new QProgramDTO(
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
                ))
                .from(program)
                .orderBy(program.programDate.desc())
                .where(
                        keywordContains(keyword), // 검색 Keyword 일치
                        programStatusEq(programStatus) // 상태 일치 확인
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QProgramDTO(
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
        ))
                .from(program)
                .fetch().size();

        return new PageImpl<>(programs,pageable,total);

    }


    /* Keyword 일치 여부 확인 메소드 */
    private BooleanExpression keywordContains(String keyword){
        return StringUtils.hasText(keyword) ? program.programName.contains(keyword) : null;
    }

    /* 상태 일치 여부 확인 메소드 */
    private BooleanExpression programStatusEq(String programStatus){
        return StringUtils.hasText(programStatus) ? (program.programStatus.stringValue()).eq(programStatus) : null;
    }

}
