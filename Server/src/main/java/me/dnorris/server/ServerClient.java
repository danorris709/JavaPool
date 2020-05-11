package me.dnorris.server;

import me.dnorris.server.database.DatabaseController;

public class ServerClient {

    private static DatabaseController databaseController;

    public static void main(String[] args) {
        databaseController = new DatabaseController("localhost", 3306, "root", "", "pool");
    }

    public static DatabaseController getDatabaseController() {
        return databaseController;
    }
}
