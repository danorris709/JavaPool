package me.dnorris.server.user;

import me.dnorris.server.database.DatabaseController;
import me.dnorris.server.user.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseController {

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS `users`(" +
            "id             INT             NOT NULL        AUTO_INCREMENT, " +
            "username       VARCHAR(64)     NOT NULL        UNIQUE, " +
            "email          LONGTEXT        NOT NULl        UNIQUE, " +
            "user_name      LONGTEXT        NOT NULL, " +
            "password       LONGTEXT        NOT NULL, " +
            "PRIMARY KEY(id));";

    private static final String SELECT_USER = "SELECT * FROM `users` WHERE username = LOWER(?);";
    private static final String SELECT_PASSWORD = "SELECT password FROM `users` WHERE username = LOWER(?);";

    private final DatabaseController databaseController;

    public UserDatabaseController(DatabaseController databaseController) {
        this.databaseController = databaseController;

        this.initTables();
    }

    private void initTables() {
        try (Connection connection = this.databaseController.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_TABLE);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        try (Connection connection = this.databaseController.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            return new User(resultSet.getLong("id"),
                    username,
                    resultSet.getString("email"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPassword(String username) {
        try (Connection connection = this.databaseController.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                return null;
            }

            return resultSet.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
