package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.admin.program.AdminProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void removeProgram(List<String> programIds){

        List<Long> programIdLong = new ArrayList<>();
        programIds.stream().map(Long::parseLong).forEach(programIdLong::add);
        programIdLong.forEach(adminProgramRepository::deleteById);

    }

}
