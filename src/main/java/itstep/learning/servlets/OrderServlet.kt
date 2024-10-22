package itstep.learning.servlets
import itstep.learning.dao.ContentDao
import itstep.learning.dao.OrderDao
import itstep.learning.entities.MainContent
import itstep.learning.entities.OrderContent
import java.io.IOException
import javax.inject.Inject
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class OrderServlet @Inject constructor(private val contentDao: ContentDao) : HttpServlet() {
    private val orderDao = OrderDao()

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username");
        val contentIds = request.getParameterValues("contentIds");
        val order: OrderContent = OrderContent();
        order.setUsername(username);
        val contents: MutableList<MainContent> = ArrayList()

        for (id in contentIds) {
            val content = contentDao.findById(id.toLong().toInt());
            if (content != null) contents.add(content);
        }

        order.setContents(contents);
        orderDao.addOrder(order);
        response.writer.write("Заказ создан для пользователя: $username");
    }
}