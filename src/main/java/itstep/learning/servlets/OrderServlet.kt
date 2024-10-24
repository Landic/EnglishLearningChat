package itstep.learning.servlets
import itstep.learning.dao.ContentDao
import itstep.learning.dao.OrderDao
import itstep.learning.entities.MainContent
import itstep.learning.entities.OrderContent
import java.io.IOException
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/order/update")
class OrderServlet @Inject constructor(private val contentDao: ContentDao) : HttpServlet() {
    @EJB
    private lateinit var orderDao: OrderDao;

    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val orderId = req.getParameter("orderId")?.toLong();
        val newDetails = req.getParameter("newDetails");

        if (orderId != null) {
            val order = orderDao.findById(orderId);
            if (order != null) {
                order.details = newDetails;
                orderDao.updateOrder(order);
                resp.writer.write("Order updated successfully");
            } else resp.writer.write("Order not found");
        } else resp.writer.write("Invalid Order ID");
    }
}