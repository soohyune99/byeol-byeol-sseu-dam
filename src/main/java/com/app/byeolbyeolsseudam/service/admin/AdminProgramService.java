package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.repository.admin.program.AdminProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminProgramService {
    private final AdminProgramRepository adminProgramRepository;

    public List<ProgramDTO> showList(){

        return adminProgramRepository.showList();
    }

    public void saveProgram(ProgramDTO programDTO){
        adminProgramRepository.save(programDTO.toEntity());
    }

    public void removeProgram(List<String> programIds){

        List<Long> programIdLong = new ArrayList<>();
        programIds.stream().map(Long::parseLong).forEach(programIdLong::add);
        programIdLong.forEach(adminProgramRepository::deleteById);

    }

    public Page<ProgramDTO> searchProgram(Pageable pageable){
        List<ProgramDTO> programs = adminProgramRepository.searchProgramPaging("", pageable);

        final Page<ProgramDTO> programList = new PageImpl<>(programs, pageable, adminProgramRepository.findAll().size());
        return programList;
    }
    public Page<ProgramDTO> searchProgramPaging(String keyword, Pageable pageable){
        List<ProgramDTO> search = adminProgramRepository.searchProgram(keyword);
        List<ProgramDTO> programs = adminProgramRepository.searchProgramPaging(keyword, pageable);

        final Page<ProgramDTO> programSearchList = new PageImpl<>(programs, pageable, search.size());
        return programSearchList;
    }

    public ProgramDTO selectById(String programIdstr){
        Long programId = Long.parseLong(programIdstr);
        return adminProgramRepository.selectById(programId);
    }

    public void updateProgram(ProgramDTO programDTO, Long programId){
        adminProgramRepository.update(programDTO);
        Program program = adminProgramRepository.findById(programId).get();
        adminProgramRepository.save(program);
    }

    public List<MyprogramDTO> showRegisterList(String programIdstr){
        Long programId = Long.parseLong(programIdstr);
        return adminProgramRepository.showRegisterList(programId);
    }

}
