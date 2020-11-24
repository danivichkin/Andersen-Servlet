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

@WebServlet(urlPatterns = {"/reg"})
public class RegistrationServlet extends HttpServlet {

    UserRepo userRepo = new UserRepo();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(name, password);
        try {
            boolean isUserExist = userRepo.isUserExist(user);
            if(isUserExist){
                req.setAttribute("userExist", "User Exist, change login");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                userRepo.createUser(user);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

    }

}
