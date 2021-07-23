package sungshin.sooon.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "post_images_id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Post_images {
    @Id
    @GeneratedValue
    private Long post_images_id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private String image_url;

    private Long order_num;
}
