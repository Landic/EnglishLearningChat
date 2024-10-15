package itstep.learning.dao;
import itstep.learning.entities.TextContent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class ContentDao {
    @PersistenceContext private EntityManager entityManager;

    @Transactional public void addText(TextContent textContent) {
        entityManager.persist(textContent);
    }
    public List<TextContent> getAllTexts() {
        return entityManager.createQuery("SELECT t FROM TextContent t", TextContent.class).getResultList();
    }
}