package me.dnorris.pool.screens;

import javax.swing.*;

public class CardScreenPanel {

    private final String identifier;
    private final JPanel jPanel = new JPanel();

    public CardScreenPanel(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public JPanel getJPanel() {
        return this.jPanel;
    }
}
