package itstep.learning.servlets;
import itstep.learning.dao.OrderDao;
import itstep.learning.entities.OrderContent;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order-history") public class OrderHistoryServlet extends HttpServlet {
    @Inject private OrderDao orderDao;

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<OrderContent> completedOrders = orderDao.getCompletedOrders(userId);
        req.setAttribute("completedOrders", completedOrders);
        req.getRequestDispatcher("/WEB-INF/views/orderHistory.jsp").forward(req, resp);
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.parseLong(req.getParameter("orderId"));
        OrderContent existingOrder = orderDao.findById(orderId);
        orderDao.repeatOrder(existingOrder);
        resp.sendRedirect(req.getContextPath() + "/order-history");
    }
}