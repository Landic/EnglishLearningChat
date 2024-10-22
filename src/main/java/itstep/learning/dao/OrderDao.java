package itstep.learning.dao;
import itstep.learning.entities.OrderContent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class OrderDao {
    @PersistenceContext private EntityManager entityManager;

    @Transactional public void addOrder(OrderContent order) {
        entityManager.persist(order);
    }
    public OrderContent findById(Long id) {
        return entityManager.find(OrderContent.class, id);
    }
}