package ma.emsi.dao.impl;

import ma.emsi.dao.UserDao;
import ma.emsi.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection connection= DB.getConnection();
    public User getUser(String username) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("User doesn't exist");
        }
        return user;
    }
}
