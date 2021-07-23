package sungshin.sooon.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private Long order_num;

    private Boolean is_anonymous;

    private LocalDateTime created_at;
}
