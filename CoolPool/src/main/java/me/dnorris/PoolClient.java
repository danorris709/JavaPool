package me.dnorris;

import me.dnorris.screens.ScreenManager;

public class PoolClient {

    private static ScreenManager screenManager;

    public static void main(String[] args) {
        screenManager = new ScreenManager();

        screenManager.initFrame();
        screenManager.openHomeScreen();
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }
}
