package hachi.simpleboard.domain.comments;

import hachi.simpleboard.domain.BaseTimeEntity;
import hachi.simpleboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comments;

    @OneToMany
    private List<User> users = new ArrayList<>();

    @Builder
    public Comments(Long id, String comments) {
        this.id = id;
        this.comments = comments;
    }
}