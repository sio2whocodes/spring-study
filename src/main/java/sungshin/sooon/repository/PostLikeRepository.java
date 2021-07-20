package sungshin.sooon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sungshin.sooon.model.Post_like;

public interface PostLikeRepository extends JpaRepository<Post_like, Long> {

}
