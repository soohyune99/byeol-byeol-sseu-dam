package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

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
                .where(program.programName.contains(keyword)).orderBy(program.programDate.desc())
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
        )).from(program).orderBy(program.programDate.desc())//.limit(9)
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
    public List<ProgramDTO> selectScrollPrograms(int page) {
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
        )).from(program).orderBy(program.programDate.desc()).offset(page * 9).limit(9).fetch();

    }


//    @Override
//    public ProgramDTO programDetailPage(Model model, Member member, Long programId) {
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
//                .where(program.programId.eq(programId))
//                .fetchOne();
//    }
//
//    @Override
//    public ProgramDTO programDetailPage1(Long programId) {
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
//                .where(program.programId.eq(programId)).fetchOne();
//    }


}