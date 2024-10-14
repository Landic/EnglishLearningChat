package itstep.learning.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private static final Map<String, String> users = new HashMap<>();

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"), password = request.getParameter("password");

        if (users.containsKey(username)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("Пользователь уже существует!");
        } else {
            users.put(username, password);
            response.getWriter().write("Регистрация успешна!");
        }
    }
}