package itstep.learning.servlets;
import itstep.learning.dao.ContentDao;
import itstep.learning.entities.MainContent;
import itstep.learning.services.SimilarContentService;
import org.jetbrains.annotations.NotNull;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public class MainContentServlet extends HttpServlet {
    private final ContentDao contentDao = new ContentDao();

    @Override protected void doGet(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String slug = request.getParameter("slug");
        if (slug != null && !slug.isEmpty()) {
            try {
                MainContent content = contentDao.findBySlug(slug);
                if (content != null) {
                    response.setContentType("application/json");
                    response.getWriter().write(content.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Content not found");
                }
            } catch (NoResultException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Content not found");
            }
        } else {
            List<MainContent> mainContents = contentDao.getAllMainContent();
            response.setContentType("application/json");
            response.getWriter().write(mainContents.toString());
        }

        int contentId = Integer.parseInt(request.getParameter("id"));
        MainContent content = contentDao.findById(contentId);

        SimilarContentService similarContentService = new SimilarContentService();
        List<MainContent> similarContent = similarContentService.getSimilarContent(content);
        request.setAttribute("similarContent", similarContent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/content.jsp");
        dispatcher.forward(request, response);
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