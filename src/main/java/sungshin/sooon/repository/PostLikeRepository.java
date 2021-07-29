package sungshin.sooon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    PostLike findByAccountAndPost(Account account, Post post);
}
