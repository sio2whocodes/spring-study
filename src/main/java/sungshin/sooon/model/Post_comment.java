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
@EqualsAndHashCode(of = "post_comment_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Post_comment {
    @Id
    @GeneratedValue
    private Long post_comment_id;

    private String comment;

    private Long account_id;

    private Long post_id;

    private Long order_num;

    private Boolean is_anonymous;

    private LocalDateTime created_at;
}
