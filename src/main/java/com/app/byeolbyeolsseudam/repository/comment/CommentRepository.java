package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
