package repo;

import entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class UserRepo {

    public static final String INSERT_NEW_USER =
            "INSERT INTO USR(login, password) VALUES (?,?)";

    public static final String GET_USER_BY_NAME =
            "SELECT login FROM usr WHERE login = (?)";

    public static final String GET_USER =
            "SELECT login, password FROM usr WHERE login = (?)";

    String url = "jdbc:postgresql://localhost:5432/servlet";
    String username = "postgres";
    String password = "vfrae0v5";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void createUser(User user) throws SQLException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.execute();
        connection.close();
    }

    public boolean isUserExist(User user) throws SQLException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(GET_USER_BY_NAME);
        preparedStatement.setString(1, user.getName());
        resultSet = preparedStatement.executeQuery();
        boolean b = false;
        while (resultSet.next()) {
            b = true;
        }
        connection.close();
        return b;
    }

    public User getUserByLogin(User user) throws SQLException, ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(GET_USER);
        preparedStatement.setString(1, user.getName());
        resultSet = preparedStatement.executeQuery();
        User newUser = new User();
        while (resultSet.next()){
            String login = resultSet.getString(1);
            String password = resultSet.getString(2);
            newUser.setName(login);
            newUser.setPassword(password);
        }
        connection.close();
        return newUser;
    }
}
