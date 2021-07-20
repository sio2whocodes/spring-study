package sungshin.sooon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sungshin.sooon.model.Post_comment;

public interface PostCommentRepository extends JpaRepository<Post_comment, Long> {
}
