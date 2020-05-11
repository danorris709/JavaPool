package me.dnorris.screens.implementation;

import me.dnorris.screens.CardScreenPanel;
import me.dnorris.screens.ScreenManager;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Home screen panel
 *
 * @author https://github.com/danorris709
 */
public class HomeScreen extends CardScreenPanel {

    public static final String HOME_SCREEN_IDENTIFIER = "HOME_SCREEN";

    private final ScreenManager screenManager;

    /**
     *
     * Constructor taking the screen manager this will be used with
     *
     * @param screenManager Screen manager controlling when this is dispalyed
     */
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

        localGameButton.addActionListener(e -> this.screenManager.openGameScreen());

        middlePanel.setLayout(new FlowLayout());

        middlePanel.add(localGameButton);

        return middlePanel;
    }
}
