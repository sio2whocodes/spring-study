package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.Post_like;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeRequestDto {
    private Account account;
    private Post post;

    @Builder
    public PostLikeRequestDto(Account account, Post post) {
        this.account = account;
        this.post = post;
    }

    public Post_like to_Post_like(){
        return Post_like.builder()
                .account(account)
                .post(post)
                .build();
    }
}
