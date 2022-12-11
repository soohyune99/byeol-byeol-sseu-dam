package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.Search;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.program.Program;
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

    /* 키워드를 입력시 검색 결과 List */
    @Override
    public List<ProgramDTO> searchProgram(String keyword) {
        return jpaQueryFactory.select(new QProgramDTO(
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
                .where(program.programName.contains(keyword))
                .orderBy(program.programDate.desc())
                .limit(12)
                .fetch();
    }

    /* 전체 program List */
    @Override
    public List<ProgramDTO> programAllList() {
        return jpaQueryFactory.select(new QProgramDTO(
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
        )).from(program).orderBy(program.programDate.desc())
                .limit(12)
                .fetch();
    }

    /* program List _ STATUS List*/
    @Override
    public List<ProgramDTO> programStatusIngList(ProgramStatus programStatus) {
        return jpaQueryFactory.select(new QProgramDTO(
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
                .where(program.programStatus.eq(programStatus)).orderBy(program.programDate.desc())
                .limit(12)
                .fetch();
    }

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
        )).from(program).where(program.programId.eq(programId)).fetchOne();
        return programDTO;
    }

    @Override
    public Page<ProgramDTO> selectScrollPrograms (Search search, Pageable pageable) {
        List<ProgramDTO> programDTOS = jpaQueryFactory.select(new QProgramDTO(
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
                        keywordContains(search.getKeyword()), // 검색 Keyword 일치
                        programStatusEq(search.getProgramStatus()) // 상태 일치 확인
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

        return new PageImpl<>(programDTOS,pageable,total);

    }

    /* Keyword 일치 여부 확인 메소드 */
    private BooleanExpression keywordContains(String keyword){
        return StringUtils.hasText(keyword) ? program.programName.contains(keyword) : null;
    }

    /* 상태 일치 여부 확인 메소드 */
    private BooleanExpression programStatusEq(String programStatus){
        return StringUtils.hasText(programStatus) ? (program.programStatus.stringValue()).eq(programStatus) : null;
    }

//    @Override
//    public List<ProgramDTO> selectScrollPrograms(Search search) {
//        return jpaQueryFactory.select(new QProgramDTO(
//                program.programId,
//                program.programName,
//                program.programPlace,
//                program.possibleDate.openingDate,
//                program.possibleDate.closingDate,
//                program.programTime,
//                program.programDate,
//                program.programContent,
//                program.programLimitCount,
//                program.programStatus,
//                program.programFileProfileName,
//                program.programFileProfilePath,
//                program.programFileProfileUuid,
//                program.programFileDetailName,
//                program.programFileDetailPath,
//                program.programFileDetailUuid,
//                program.createdDate
//        )).from(program).orderBy(program.programDate.desc())
//                .where(
//                        keywordContains(search.getKeyword()), // 검색 Keyword 일치
//                        programStatusEq(search.getProgramStatus()) // 상태 일치 확인
//                )
//                .offset(search.getPage() * 9).limit(9).fetch();
//
//    }


    /* ==============================================================================================*/


    @Override
    public ProgramDTO programDetailPage(Model model, Member member, Long programId) {
        return jpaQueryFactory.select(new QProgramDTO(
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
    }

    @Override
    public ProgramDTO programDetailPage1(Long programId) {
        return jpaQueryFactory.select(new QProgramDTO(
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
                .where(program.programId.eq(programId)).fetchOne();
    }


}