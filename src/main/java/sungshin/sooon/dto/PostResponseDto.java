package sungshin.sooon.dto;

import lombok.Getter;
import sungshin.sooon.model.Post;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String content;
    private Long account_id;
    private Boolean is_anonymous;
    private LocalDateTime created_at;

    public PostResponseDto(Post post) {
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.account_id = post.getAccount_id();
        this.is_anonymous = post.is_anonymous();
        this.created_at = post.getCreated_at();
    }
}
