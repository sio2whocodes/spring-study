package sungshin.sooon.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "post_like_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Post_like {
    @Id
    @GeneratedValue
    private Long post_like_id;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

}
