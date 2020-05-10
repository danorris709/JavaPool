package me.dnorris.pool.screens;

import javax.swing.*;

/**
 *
 * Simple base class for any panels used
 *
 * @author https://github.com/danorris709
 */
public class CardScreenPanel {

    private final String identifier;
    private final JPanel jPanel = new JPanel();

    /**
     *
     * Basic constructor taking the identifier used for the card
     *
     * @param identifier identifier for the card
     */
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
