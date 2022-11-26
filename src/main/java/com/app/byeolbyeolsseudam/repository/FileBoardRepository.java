package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.FileBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileBoardRepository extends JpaRepository<FileBoard, Long> {
}
