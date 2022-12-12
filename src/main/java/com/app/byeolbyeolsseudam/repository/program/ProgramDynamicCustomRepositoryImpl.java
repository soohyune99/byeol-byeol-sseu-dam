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

        /* 계산될 전체 개수에서도 확인 가능해야함 _ 처음에는 where절이 안붙어있어서 전체 총개수만 가져왔었는데 이 부분에다가도 where절을 붙여서 해당 검색, 상태별 count가 가능하도록 진행 */
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
                .where(
                        keywordContains(keyword), // 검색 Keyword 일치
                        programStatusEq(programStatus) // 상태 일치 확인
                )
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
