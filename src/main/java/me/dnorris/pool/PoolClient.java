package me.dnorris.pool;

import me.dnorris.pool.screens.ScreenManager;

public class PoolClient {

    public static void main(String[] args) {
        ScreenManager screenManager = new ScreenManager();

        screenManager.initFrame();
        screenManager.openHomeScreen();
    }
}
