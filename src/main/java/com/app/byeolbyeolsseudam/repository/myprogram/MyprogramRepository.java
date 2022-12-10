package com.app.byeolbyeolsseudam.repository.myprogram;

import com.app.byeolbyeolsseudam.entity.myprogram.Myprogram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyprogramRepository extends JpaRepository<Myprogram, Long>, MyprogramCustomRepository {
}
