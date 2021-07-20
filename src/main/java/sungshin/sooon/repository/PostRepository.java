package sungshin.sooon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sungshin.sooon.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
