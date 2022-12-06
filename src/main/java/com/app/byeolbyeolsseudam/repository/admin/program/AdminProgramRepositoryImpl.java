package com.app.byeolbyeolsseudam.repository.admin.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        )).from(program).orderBy(program.programId.desc()).limit(10).fetch();
    }

//    public void delete(List<String> programId){
//        List<Long> programIdLong = null;
//        programId.stream().map(Long::parseLong).forEach(programIdLong::add);
//
//
//    }


}
