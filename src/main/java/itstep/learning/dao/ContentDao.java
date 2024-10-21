package itstep.learning.dao;
import itstep.learning.entities.MainContent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.query.Query;
import javax.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ContentDao {
    @PersistenceContext private EntityManager entityManager;
    private final SessionFactory sessionFactory;

    @Inject public ContentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
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
    public MainContent findById(Integer id) {
        return entityManager.find(MainContent.class, id);
    }
    public List<MainContent> findSimilarContent(MainContent content) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM MainContent WHERE category = :category AND id != :id";
            Query<MainContent> query = session.createQuery(hql, MainContent.class);
            query.setParameter("category", content.getCategory());
            query.setParameter("id", content.getId());
            return query.getResultList();
        }
    }
}