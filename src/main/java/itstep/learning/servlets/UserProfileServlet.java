package itstep.learning.servlets;
import itstep.learning.entities.User;
import itstep.learning.services.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {
    private UserService userService;

    @Override public void init() {
        userService = new UserService();
    }
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = userService.getUserById(request.getUserPrincipal().getName());
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}