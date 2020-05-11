package me.dnorris.server.user;

import me.dnorris.server.database.DatabaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDatabaseController {

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS `users`(" +
            "id             INT             NOT NULL        AUTO_INCREMENT, " +
            "username       VARCHAR(64)     NOT NULL        UNIQUE, " +
            "email          LONGTEXT        NOT NULl        UNIQUE, " +
            "name           LONGTEXT        NOT NULL, " +
            "password       LONGTEXT        NOT NULL, " +
            "PRIMARY KEY(id));";

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
}
