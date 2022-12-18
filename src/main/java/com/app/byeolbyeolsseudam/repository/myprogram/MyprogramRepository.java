package com.app.byeolbyeolsseudam.repository.myprogram;

import com.app.byeolbyeolsseudam.entity.myprogram.Myprogram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyprogramRepository extends JpaRepository<Myprogram, Long>, MyprogramCustomRepository {

    public int countByMemberMemberId(Long memberId);

    // 프로그램 페이지에서 디테일페이지 프로그램 아이디랑 일치하는지 확인
    public List<Myprogram> findByProgram_ProgramId(Long programId);

}
