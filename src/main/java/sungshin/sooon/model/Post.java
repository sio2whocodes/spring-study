package sungshin.sooon.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "post_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue
    private long post_id;

    private String title;

    private String content;

    private long account_id;

    private boolean is_anonymous;

    private LocalDateTime created_at;

    public void update(String title, String content, Boolean is_anonymous){
        this.title = title;
        this.content = content;
        this.is_anonymous = is_anonymous;
    }
}
