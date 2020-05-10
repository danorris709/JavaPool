package me.dnorris.pool.screens;

import com.google.common.collect.Maps;
import me.dnorris.pool.screens.implementation.FinishScreen;
import me.dnorris.pool.screens.implementation.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

public class ScreenManager {

    private Map<Class<? extends CardScreenPanel>, CardScreenPanel> screenPanels = Maps.newHashMap();

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardPanelLayout;

    public void addNewScreen(CardScreenPanel panel) {
        this.screenPanels.put(panel.getClass(), panel);
        cardPanel.add(panel.getIdentifier(), panel.getJPanel());
    }

    @SuppressWarnings("unchecked")
    public <T extends CardScreenPanel> Optional<T> getPanelFromIdentifier(Class<T> clazz) {
        return Optional.ofNullable((T) this.screenPanels.get(clazz));
    }

     public void initFrame() {
         this.setupFrame();
         this.setupCardPanel();

         this.openHomeScreen();

         frame.add(cardPanel);
         frame.pack();
         frame.setVisible(true);
     }

     private void setupFrame() {
         this.frame = new JFrame("Hoppers");
         this.frame.setSize(600, 600);
         this.frame.setPreferredSize(new Dimension(600, 600));
         this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     }

     private void setupCardPanel() {
         this.cardPanel = new JPanel();
         this.cardPanelLayout = new CardLayout();
         this.cardPanel.setLayout(this.cardPanelLayout);

         this.addNewScreen(new HomeScreen(this));
         this.addNewScreen(new FinishScreen(this));
     }

    public void openHomeScreen() {
        this.openFromIdentifier(HomeScreen.class);
    }

    public void openGameScreen() {
        // TODO: 10/05/2020 open game arena
    }

    public void finishGame(String winner, Instant startTime) {
        // TODO: 10/05/2020 delete game frame
        this.frame.setVisible(true);
        this.openFinishScreen(winner, startTime);
    }

    public void openFinishScreen(String winner, Instant startTime) {
        this.openFromIdentifier(FinishScreen.class);
        this.getPanelFromIdentifier(FinishScreen.class).ifPresent(finishScreen -> finishScreen.setState(winner, startTime));
    }

    private void openFromIdentifier(Class<? extends CardScreenPanel> clazz) {
        this.getPanelFromIdentifier(clazz).ifPresent(cardScreenPanel -> this.cardPanelLayout.show(this.cardPanel, cardScreenPanel.getIdentifier()));
    }
}
