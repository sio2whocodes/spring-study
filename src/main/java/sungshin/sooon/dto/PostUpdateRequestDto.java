package sungshin.sooon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private Boolean is_anonymous;

    @Builder
    public PostUpdateRequestDto(String title, String content, Boolean is_anonymous) {
        this.title = title;
        this.content = content;
        this.is_anonymous = is_anonymous;
    }
}
