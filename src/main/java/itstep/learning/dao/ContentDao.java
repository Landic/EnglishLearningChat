package itstep.learning.dao;
import itstep.learning.entities.MainContent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class ContentDao {
    @PersistenceContext private EntityManager entityManager;
    @Transactional public void addMainContent(MainContent mainContent) {
        entityManager.persist(mainContent);
    }
    public List<MainContent> getAllMainContent() {
        return entityManager.createQuery("SELECT m FROM MainContent m", MainContent.class).getResultList();
    }
    @Transactional public void updateMainContent(MainContent mainContent) {
        entityManager.merge(mainContent);
    }
    @Transactional public void deleteMainContent(Long id) {
        MainContent mainContent = entityManager.find(MainContent.class, id);
        if (mainContent != null) entityManager.remove(mainContent);
    }
    public MainContent findBySlug(String slug) {
        return entityManager.createQuery("SELECT c FROM MainContent c WHERE c.slug = :slug", MainContent.class).setParameter("slug", slug).getSingleResult();
    }
}