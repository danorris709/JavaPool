package me.dnorris.pool.screens.implementation;

import me.dnorris.pool.screens.CardScreenPanel;
import me.dnorris.pool.screens.ScreenManager;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends CardScreenPanel {

    public static final String HOME_SCREEN_IDENTIFIER = "HOME_SCREEN";

    private final ScreenManager screenManager;

    public HomeScreen(ScreenManager screenManager) {
        super(HOME_SCREEN_IDENTIFIER);

        this.screenManager = screenManager;
        this.initHomePanel();
    }

    private void initHomePanel() {
        JPanel homePanel = new JPanel();

        homePanel.setLayout(new BorderLayout());
        homePanel.add(this.initTopPanel(), BorderLayout.NORTH);
        homePanel.add(this.initCenterPanel(), BorderLayout.CENTER);

        this.getJPanel().add(homePanel);
    }

    private JPanel initTopPanel() {
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new GridLayout(5, 1));

        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel("<html><h1><b>Cool Pool v 1.0</b></h1></html>", SwingConstants.CENTER));
        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel( "   "));

        return topPanel;
    }

    private JPanel initCenterPanel() {
        JPanel middlePanel = new JPanel();
        JButton localGameButton = new JButton("Play local game");

        // TODO: 10/05/2020 add action listener to start game

        middlePanel.setLayout(new FlowLayout());

        middlePanel.add(localGameButton);

        return middlePanel;
    }
}
