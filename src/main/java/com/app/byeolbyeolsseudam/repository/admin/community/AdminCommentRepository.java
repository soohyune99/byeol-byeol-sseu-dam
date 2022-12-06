package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCommentRepository extends JpaRepository<Comment, Long>, AdminCommentCustomRepository {
}
