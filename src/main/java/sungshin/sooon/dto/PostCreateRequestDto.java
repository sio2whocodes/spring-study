package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Account account;
    private boolean is_anonymous;
    private LocalDateTime created_at;

    @Builder
    public PostCreateRequestDto(String title, String content, Account account, boolean is_anonymous) {
        this.title = title;
        this.content = content;
        this.account = account;
        this.is_anonymous = is_anonymous;
        this.created_at = LocalDateTime.now();
    }

    public Post toPost(){
        return Post.builder()
                .title(title)
                .content(content)
                .account(account)
                .is_anonymous(is_anonymous)
                .created_at(LocalDateTime.now())
                .build();
    }

}
