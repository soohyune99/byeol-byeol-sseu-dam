//package com.app.byeolbyeolsseudam.repository.program;
//
//import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
//import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
//import com.app.byeolbyeolsseudam.domain.Search;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
//import static com.app.byeolbyeolsseudam.entity.program.QProgram.program;
//
//@Repository
//@RequiredArgsConstructor
//public class ProgramDynamicCustomRepositoryImpl implements ProgramDynamicCustomRepository {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    /* 동적쿼리 - 검색 => 프로그램 상태 , 프로그램 List */
//    @Override
//    public List<ProgramDTO> programDynamicList(Search search) {
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
//        )).from(program)
//                .where(
//                        keywordContains(search.getKeyword()), // 검색 Keyword 일치
//                        programStatusEq(search.getProgramStatus()) // 상태 일치 확인
////                        keywordContainsAndProgramStatusEq(search.getKeyword(),search.getProgramStatus()) //keyword 와 상태 함께 일치 (검색어와 상태 확인)
//                )
//                .fetch();
//    }
//
//    /* Keyword 일치 여부 확인 메소드 */
//    private BooleanExpression keywordContains(String keyword){
//        return StringUtils.hasText(keyword) ? program.programName.contains(keyword) : null;
//    }
//
//    /* 상태 일치 여부 확인 메소드 */
//    private BooleanExpression programStatusEq(String programStatus){
//        return StringUtils.hasText(programStatus) ? (program.programStatus.stringValue()).eq(programStatus) : null;
//    }
//
//    /* keyword 와 상태 함께 일치 (검색어와 상태 확인)  */
//    private BooleanExpression keywordContainsAndProgramStatusEq (String keyword, String programStatus){
//        return keywordContains(keyword).and(programStatusEq(programStatus));
//    }
//
//}
