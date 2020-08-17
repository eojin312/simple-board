package hachi.simpleboard.domain.category;


import javax.persistence.*;
import java.util.List;

@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<CategoryMapping> categoryMappings;

}

