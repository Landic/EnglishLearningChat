package itstep.learning.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthServlet extends HttpServlet {
    private static final String SECRET_KEY = "13082004";

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getAttribute("username");

        if (username != null) response.getWriter().write("Текущий пользователь: " + username);
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Неавторизован.");
        }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"), password = request.getParameter("password");

        Map<String, String> users = new HashMap<>();
        users.put("admin", "password");

        if (users.containsKey(username)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("Пользователь уже существует!");
        } else {
            users.put(username, password);
            response.getWriter().write("Регистрация успешна для пользователя: " + username);
        }
    }
    @Override protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getAttribute("username");

        if (username != null) response.getWriter().write("Пользователь " + username + " удалён.");
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Неавторизован.");
        }
    }
}