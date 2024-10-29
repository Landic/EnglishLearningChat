package itstep.learning.dao;
import itstep.learning.entities.OrderContent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class OrderDao {
    @PersistenceContext private EntityManager entityManager;

    @Transactional public void addOrder(OrderContent order) {
        entityManager.persist(order);
    }
    public List<OrderContent> getCompletedOrders(Long userId) {
        return entityManager.createQuery("SELECT o FROM OrderContent o WHERE o.userId = :userId AND o.status = 'completed'", OrderContent.class).setParameter("userId", userId).getResultList();
    }
    public OrderContent findById(Long id) {
        return entityManager.find(OrderContent.class, id);
    }
    public boolean updateOrder(OrderContent orderContent) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(orderContent);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    @Transactional public void deleteOrder(Long id) {
        OrderContent order = entityManager.find(OrderContent.class, id);
        if (order != null) entityManager.remove(order);
    }
    @Transactional public OrderContent repeatOrder(OrderContent existingOrder) {
        OrderContent newOrder = new OrderContent();
        newOrder.setUserId(existingOrder.getUserId());
        newOrder.setContentId(existingOrder.getContentId());
        newOrder.setStatus("active");  // Новый заказ имеет статус active

        entityManager.persist(newOrder);
        return newOrder;
    }
}