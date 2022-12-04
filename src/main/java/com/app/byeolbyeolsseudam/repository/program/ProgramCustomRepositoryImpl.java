package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        )).from(program)
                .fetch();
    }

    /* program List _ STATUS _ 모집중 */
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
                .where(program.programStatus.eq(programStatus))
                .fetch();
    }
}
