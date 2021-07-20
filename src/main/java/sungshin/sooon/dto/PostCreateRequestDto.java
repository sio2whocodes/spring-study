package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sungshin.sooon.model.Post;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Long account_id;
    private boolean is_anonymous;
    private LocalDateTime created_at;

    @Builder
    public PostCreateRequestDto(String title, String content, Long user_id, boolean is_anonymous) {
        this.title = title;
        this.content = content;
        this.account_id = user_id;
        this.is_anonymous = is_anonymous;
        this.created_at = LocalDateTime.now();
    }

    public Post toPost(){
        return Post.builder()
                .title(title)
                .content(content)
                .account_id(account_id)
                .is_anonymous(is_anonymous)
                .created_at(LocalDateTime.now())
                .build();
    }

}
