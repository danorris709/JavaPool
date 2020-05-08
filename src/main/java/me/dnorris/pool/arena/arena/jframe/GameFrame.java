package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.GameArena;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * Custom implementation of the {@link JFrame} for handling the {@link JFrame} implementation of the
 * {@link GameArena} interface.
 * Handles the painting and rendering of entities and updating the dimensions of the arena.
 *
 * @author https://github.com/danorris709
 */
public class GameFrame extends JFrame {

    private final GameArena gameArena;

    private boolean rendered = false;

    /**
     *
     * Basic constructor
     *
     * @param gameArena The game arena being drawn
     */
    public GameFrame(GameArena gameArena) {
        this.gameArena = gameArena;

        this.setTitle("Let's Play!");
        this.setSize(this.gameArena.getDimensions());
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void setSize(int width, int height) {
        Dimension dimension = new Dimension(width, height);

        this.gameArena.setDimensions(dimension);
        super.setSize(dimension.width, dimension.height);
    }

    @Override
    public void paint(Graphics graphics) {
        if (!this.rendered) {
            this.setSize(this.gameArena.getDimensions());
            this.rendered = true;
        }

        Graphics2D window = (Graphics2D) graphics;
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();

        window.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        window.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        graphics2D.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.gameArena.tick(graphics2D);
        window.drawImage(image, this.getInsets().left, this.getInsets().top, this);
    }
}
