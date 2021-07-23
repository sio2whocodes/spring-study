package sungshin.sooon.dto;

import lombok.Getter;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.Post_comment;

import java.time.LocalDateTime;

@Getter
public class PostCommentResponseDto {
    private Long post_comment_id;
    private String comment;
    private Post post;
    private Account account;
    private Long order_num;
    private boolean is_anonymous;
    private LocalDateTime created_at;

    public PostCommentResponseDto(Post_comment post_comment) {
        this.post_comment_id = post_comment.getPost_comment_id();
        this.comment = post_comment.getComment();
        this.post = post_comment.getPost();
        this.account = post_comment.getAccount();
        this.order_num = post_comment.getOrder_num();
        this.is_anonymous = post_comment.getIs_anonymous();
        this.created_at = post_comment.getCreated_at();
    }
}
