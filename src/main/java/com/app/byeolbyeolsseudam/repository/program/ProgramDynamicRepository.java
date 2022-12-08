package com.app.byeolbyeolsseudam.repository.program;

import com.app.byeolbyeolsseudam.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramDynamicRepository extends JpaRepository<Program, Long>,ProgramDynamicCustomRepository {
}
