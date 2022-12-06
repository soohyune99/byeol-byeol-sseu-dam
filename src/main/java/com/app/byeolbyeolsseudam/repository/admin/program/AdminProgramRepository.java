package com.app.byeolbyeolsseudam.repository.admin.program;

import com.app.byeolbyeolsseudam.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProgramRepository extends JpaRepository<Program, Long>, AdminProgramCustomRepository {
}
