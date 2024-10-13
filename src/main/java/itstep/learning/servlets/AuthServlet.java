package itstep.learning.servlets;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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

        if ("admin".equals(username) && "password".equals(password)) {
            String token = Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_00))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
            response.setHeader("Authorization", "Bearer " + token);
            response.getWriter().write("Вход успешен!");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Недействительные учетные данные!");
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