package itstep.learning.servlets;
import itstep.learning.dao.ContentDao;
import itstep.learning.entities.TextContent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContentDao contentDao = new ContentDao();
        List<TextContent> texts = contentDao.getAllTexts();
        response.getWriter().write(texts.toString());
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.getWriter().write("Пользователь " + username + " создан.");
    }
    @Override protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.getWriter().write("Пользователь " + username + " изменён.");
    }
    @Override protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.getWriter().write("Пользователь " + username + " удалён.");
    }
}