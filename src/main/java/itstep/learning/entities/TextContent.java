package itstep.learning.entities;
import javax.persistence.*;

@Entity
@Table(name = "texts")
public class TextContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Column(length = 5000) private String content;
}