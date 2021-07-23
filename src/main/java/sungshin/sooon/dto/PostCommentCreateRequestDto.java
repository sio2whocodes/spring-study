package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.Post_comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentCreateRequestDto {
    private String comment;
    private Account account;
    private Post post;
    private Long order_num;
    private Boolean is_anonymous;
    private LocalDateTime created_at;

    @Builder
    public PostCommentCreateRequestDto(String comment, Account account, Post post, Long order_num, Boolean is_anonymous, LocalDateTime created_at) {
        this.comment = comment;
        this.account = account;
        this.post = post;
        this.order_num = order_num;
        this.is_anonymous = is_anonymous;
        this.created_at = created_at;
    }

    public Post_comment toPost_comment(){
        return Post_comment.builder()
                .comment(comment)
                .account(account)
                .post(post)
                .order_num(order_num)
                .is_anonymous(is_anonymous)
                .created_at(LocalDateTime.now())
                .build();
    }
}
