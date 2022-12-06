package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.admin.program.AdminProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminProgramService {
    private final AdminProgramRepository adminProgramRepository;

    public List<ProgramDTO> showList(){
        return adminProgramRepository.showList();
    }
}
