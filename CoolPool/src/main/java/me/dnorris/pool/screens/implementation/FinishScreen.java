package me.dnorris.pool.screens.implementation;

import me.dnorris.pool.screens.CardScreenPanel;
import me.dnorris.pool.screens.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.TimeZone;

/**
 *
 * Finish screen panel
 *
 * @author https://github.com/danorris709
 */
public class FinishScreen extends CardScreenPanel {

    private static final String FINISH_SCREEN_IDENTIFIER = "FINISH";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private final ScreenManager screenManager;

    private JLabel stateLabel;
    private JLabel timeLabel;

    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     *
     * Constructor taking the screen manager this will be used with
     *
     * @param screenManager Screen manager controlling when this is dispalyed
     */
    public FinishScreen(ScreenManager screenManager) {
        super(FINISH_SCREEN_IDENTIFIER);

        this.screenManager = screenManager;
        this.initEndScreen();
    }

    private void initEndScreen() {
        JPanel levelPanel = new JPanel();

        levelPanel.setLayout(new BorderLayout());
        levelPanel.add(this.initTopPanel(), BorderLayout.NORTH);
        levelPanel.add(this.initCenterPanel(), BorderLayout.CENTER);
        levelPanel.add(this.initBottomPanel(), BorderLayout.SOUTH);

        this.getJPanel().add(levelPanel);
    }

    private JPanel initTopPanel() {
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new GridLayout(5, 1));

        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel("<html><h2><b>Cool Pool</b></h2></html>", SwingConstants.CENTER));
        topPanel.add(new JLabel( "   "));
        topPanel.add(new JLabel( "   "));

        return topPanel;
    }

    private JPanel initCenterPanel() {
        JPanel centerPanel = new JPanel();
        this.stateLabel = new JLabel();
        this.timeLabel = new JLabel();

        centerPanel.setLayout(new GridLayout(5, 1));
        centerPanel.add(new JLabel( "   "));
        centerPanel.add(new JLabel( "   "));
        centerPanel.add(stateLabel);
        centerPanel.add(new JLabel( "   "));
        centerPanel.add(this.timeLabel);
        centerPanel.add(new JLabel( "   "));
        centerPanel.add(new JLabel( "   "));

        return centerPanel;
    }

    private JPanel initBottomPanel() {
        JPanel buttonPanel = new JPanel();

        this.addHomeButton(buttonPanel);

        return buttonPanel;
    }

    private void addHomeButton(JPanel buttonPanel) {
        JButton homeButton = new JButton("Home");

        homeButton.addActionListener(e -> this.screenManager.openHomeScreen());

        buttonPanel.add(homeButton);
    }

    public void setState(String winner, Instant startTime) {
        String timeFormat = DATE_FORMAT.format(Duration.between(startTime, Instant.now()).toMillis());

        this.stateLabel.setText("<html><h2><b>" + winner + "</b></h2></html>");
        this.timeLabel.setText("<html><h3><b>Time Elapsed: " + timeFormat + "</b></h3></html>");
    }
}
