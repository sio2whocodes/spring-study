package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sungshin.sooon.model.Post_like;

@Getter
@Setter
@NoArgsConstructor
public class PostLikeRequestDto {
    private Long account_id;
    private Long post_id;

    @Builder
    public PostLikeRequestDto(Long account_id, Long post_id) {
        this.account_id = account_id;
        this.post_id = post_id;
    }

    public Post_like to_Post_like(){
        return Post_like.builder()
                .account_id(account_id)
                .post_id(post_id)
                .build();
    }
}
