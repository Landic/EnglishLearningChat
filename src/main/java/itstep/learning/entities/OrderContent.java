package itstep.learning.entities;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderContent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "user_id") private Long userId;
    @Column(name = "content_id") private Long contentId;
    @Column(name = "status") private String status;
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
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getContentId() { return contentId; }
    public void setContentId(Long contentId) { this.contentId = contentId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<MainContent> getContents() { return contents; }
    public void setContents(List<MainContent> contents) { this.contents = contents; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}