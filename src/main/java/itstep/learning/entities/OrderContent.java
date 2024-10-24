package itstep.learning.entities;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "order_content",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<MainContent> contents;
    private String username;
    @Nullable public String details;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<MainContent> getContents() { return contents; }
    public void setContents(List<MainContent> contents) { this.contents = contents; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}