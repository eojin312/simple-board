package hachi.simpleboard.domain.comments;

import hachi.simpleboard.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comments;

    @OneToMany
    private List<User> users = new ArrayList<>();

}
