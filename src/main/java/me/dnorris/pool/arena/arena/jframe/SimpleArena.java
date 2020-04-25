package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.arena.AbstractGameArena;

import javax.swing.*;
import java.awt.*;

public class SimpleArena extends AbstractGameArena {

    public SimpleArena(Dimension dimensions) {
        super(dimensions);

        JFrame jFrame = new GameFrame(this);

        new Thread(() -> {
            while(true) {
                jFrame.repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void tick(Graphics2D graphics) {
        for (Entity entity : this.getEntities()) {
            entity.paint(graphics);
            entity.tick();
        }
    }
}
