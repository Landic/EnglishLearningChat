package itstep.learning.servlets;
import itstep.learning.dao.ContentDao;
import itstep.learning.entities.MainContent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public class MainContentServlet extends HttpServlet {
    private final ContentDao contentDao = new ContentDao();

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MainContent> mainContents = contentDao.getAllMainContent();
        response.getWriter().write(mainContents.toString());
    }
    @Override
    @Transactional
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title"), description = request.getParameter("description");
        MainContent mainContent = new MainContent();
        mainContent.setTitle(title);
        mainContent.setDescription(description);

        contentDao.addMainContent(mainContent);
        response.getWriter().write("Основной контент добавлен: " + title);
    }
    @Override
    @Transactional
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        MainContent mainContent = new MainContent();
        mainContent.setId(id);
        mainContent.setTitle(title);
        mainContent.setDescription(description);

        contentDao.updateMainContent(mainContent);
        response.getWriter().write("Основной контент обновлен: " + title);
    }
    @Override
    @Transactional
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        contentDao.deleteMainContent(id);
        response.getWriter().write("Основной контент удален");
    }
}