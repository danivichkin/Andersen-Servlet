package servlets;

import entity.User;
import repo.UserRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User valUser = userRepo.getUserByLogin(new User(login, null));
            if(login.equals(valUser.getName().trim()) && password.equals((valUser.getPassword()).trim())){
                req.setAttribute("login", valUser);
                req.setAttribute("role", valUser.getRole());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/enter.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                req.setAttribute("invalidUser", "Invalid data");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(req, resp);
            }

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }
}
}
