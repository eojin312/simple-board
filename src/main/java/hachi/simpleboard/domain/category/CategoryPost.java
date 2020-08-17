package hachi.simpleboard.domain.category;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.post.Post;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class CategoryPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_mapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
