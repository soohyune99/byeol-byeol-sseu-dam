package com.app.byeolbyeolsseudam.repository.admin.program;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.QMyprogramDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram.myprogram;
import static com.app.byeolbyeolsseudam.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class AdminProgramRepositoryImpl implements AdminProgramCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProgramDTO> showList() {
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
        )).from(program).orderBy(program.programId.desc()).limit(3).fetch();
    }

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
                .where(program.programName.contains(keyword).or(program.programContent.contains(keyword)))
                .orderBy(program.programId.desc())
                .fetch();
    }

    @Override
    public List<ProgramDTO> searchProgramPaging(String keyword, Pageable pageable) {
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
                .where(program.programName.contains(keyword).or(program.programContent.contains(keyword)))
                .orderBy(program.programId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public ProgramDTO selectById(Long programId) {
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
    public void update(ProgramDTO programDTO) {
        jpaQueryFactory.selectFrom(program)
                .where(program.programId.eq(programDTO.getProgramId()))
                .fetchOne().update(programDTO);
    }

    @Override
    public List<MyprogramDTO> showRegisterList(Long programId) {
        return jpaQueryFactory.select(new QMyprogramDTO(myprogram.myprogramId,
                myprogram.myprogramStatus, myprogram.member.memberId, myprogram.member.memberName, myprogram.program.programId,
                myprogram.program.programName, myprogram.program.programPlace,
                myprogram.program.programDate, myprogram.program.programFileProfileName))
                .from(myprogram).where(myprogram.program.programId.eq(programId))
                .orderBy(myprogram.createdDate.desc())
                .fetch();
    }
}
